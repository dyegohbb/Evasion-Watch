package br.com.evasion.watch.services;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.evasion.watch.exceptions.EwException;
import br.com.evasion.watch.exceptions.FileNotSupportedException;
import br.com.evasion.watch.exceptions.NoFileContentException;
import br.com.evasion.watch.models.embeddables.StudentDataFeatures;
import br.com.evasion.watch.models.entities.CsvImportHistory;
import br.com.evasion.watch.models.entities.StudentData;
import br.com.evasion.watch.models.entities.User;
import br.com.evasion.watch.models.enums.SituationEnum;
import br.com.evasion.watch.models.enums.StudentDataExtensionType;
import br.com.evasion.watch.models.transfer.ApiResponseObject;
import br.com.evasion.watch.repositories.CsvImportHistoryRepository;

@Service
public class StudentDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentDataService.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CsvImportHistoryRepository importHistoryRepository;

	public ApiResponseObject importStudentData(String authorization, MultipartFile studentDataCsv) {

		try {
			LOGGER.info("[IMPORTAÇÃO DE DADOS] Iniciando validações dos dados.");
			User user = userService.findUserByToken(authorization);

			if (!studentDataCsv.isEmpty()) {
				String fileName = studentDataCsv.getOriginalFilename();

				if (fileName != null) {
					String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
					LOGGER.info("[IMPORTAÇÃO DE DADOS] Extensão do arquivo enviado: {}", fileExtension);
					if (!StudentDataExtensionType.isValidExtension(fileExtension)) {
						LOGGER.error("[IMPORTAÇÃO DE DADOS] Extensão do arquivo não suportada: {}", fileExtension);
						throw new FileNotSupportedException(fileExtension);
					}
				} else {
					throw new NoFileContentException();
				}
				
				LOGGER.info("[IMPORTAÇÃO DE DADOS] Salvando histórico inicial da importação");
				CsvImportHistory importHistory = new CsvImportHistory();
				importHistory.setFileName(fileName);
				importHistory.setFileSize(studentDataCsv.getSize());
				importHistory.setSituation(SituationEnum.RUNNING);
				importHistory.setUser(user);
				importHistoryRepository.save(importHistory);
				LOGGER.info("[IMPORTAÇÃO DE DADOS] Histórico inicial da importação salvo com sucesso!");
				
				return this.processStudentDataCsv(studentDataCsv, importHistory);
			}
			throw new NoFileContentException();

		} catch (EwException e) {
			LOGGER.error(String.format("[IMPORTAÇÃO DE DADOS] Erro na importação de dados, motivo: %s", e.getMessage()));
			return new ApiResponseObject(e);
		}

	}

	private ApiResponseObject processStudentDataCsv(MultipartFile studentDataCsv, CsvImportHistory importHistory) {
		LOGGER.info("[IMPORTAÇÃO DE DADOS] Iniciando processamento dos dados.");
		CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setDelimiter(";").setIgnoreEmptyLines(true)
				.setSkipHeaderRecord(true).setHeader().build();
		int rowCount = 0;

		try (CSVParser csvParser = new CSVParser(
				new InputStreamReader(studentDataCsv.getInputStream(), StandardCharsets.ISO_8859_1), csvFormat)) {

			Map<String, Integer> headerMap = csvParser.getHeaderMap();
			List<StudentData> dataList = csvParser.stream().parallel()
					.map(csvRecord -> this.prepareDatafromCSVRecord(csvRecord, headerMap)).toList();

			rowCount = prepareAndSaveCsvImportHistory(dataList, importHistory);

		} catch (EwException e) {
			LOGGER.error("Erro ao processar dados, motivo: {}", e.getMessage());
			return new ApiResponseObject(e);
		} catch (Exception e) {
			LOGGER.error("Erro ao processar dados, motivo: {}", e.getMessage());
			return new ApiResponseObject(new EwException(
					String.format("Erro ao processar dados, motivo: %s", e.getMessage()), HttpStatus.BAD_REQUEST));
		}

		LOGGER.info("[IMPORTAÇÃO DE DADOS] Importação concluida com sucesso!");
		return new ApiResponseObject(
				String.format("Dados processados com sucesso, registros adicionados: %d", rowCount), HttpStatus.OK);
	}

	private int prepareAndSaveCsvImportHistory(List<StudentData> dataList, CsvImportHistory importHistory) throws EwException {
		
		int rowCount = dataList.size();
		LOGGER.info("[IMPORTAÇÃO DE DADOS] Iniciando salvamento dos dados, quantidade: {}", rowCount);
		
		try {
			importHistory.setRowCount(rowCount);
			importHistory.setSituation(SituationEnum.SUCCESS);
			importHistory.setStudentDatas(dataList);

			importHistoryRepository.save(importHistory);
		} catch (Exception e) {
			importHistory.setSituation(SituationEnum.ERROR);
			importHistoryRepository.save(importHistory);
			throw new EwException(
					String.format("Erro ao salvar dados no banco de dados, motivo: %s", e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
		
		LOGGER.info("[IMPORTAÇÃO DE DADOS] Salvamento dos dados concluida com sucesso! Dados salvos: {}", rowCount);
		return rowCount;
	}

	private StudentData prepareDatafromCSVRecord(CSVRecord csvRecord, Map<String, Integer> headerMap) {
		StudentData data = new StudentData();
		data.setName(getCsvRecordValueOrDefault(csvRecord, headerMap, "clNome_Pessoa", ""));
		data.setStudentID(getCsvRecordValueOrDefault(csvRecord, headerMap, "Matricula", ""));

		StudentDataFeatures features = new StudentDataFeatures();
		features.setDescricaoModalidadeCurso(
				getCsvRecordValueOrDefault(csvRecord, headerMap, "Desc_Modalidade_Curso", ""));

		features.setDescricaoCota(getCsvRecordValueOrDefault(csvRecord, headerMap, "Desc_Cota", ""));
		features.setNivelEnsino(getCsvRecordValueOrDefault(csvRecord, headerMap, "Nivel_Ensino", ""));
		features.setDescricaoSituacaoMatricula(
				getCsvRecordValueOrDefault(csvRecord, headerMap, "Desc_Sit_Matricula", ""));

		features.setDescricaoEstadoCivil(getCsvRecordValueOrDefault(csvRecord, headerMap, "Desc_Estado_Civil", ""));
		features.setDescricaoTurno(getCsvRecordValueOrDefault(csvRecord, headerMap, "Desc_Turno", ""));
		features.setDescricaoNaturalidade(getCsvRecordValueOrDefault(csvRecord, headerMap, "Desc_Naturalidade", ""));
		features.setCoeficienteRendimento(
				getCsvRecordValueOrDefault(csvRecord, headerMap, "Coeficiente_Rendimento", ""));

		features.setDescricaoCor(getCsvRecordValueOrDefault(csvRecord, headerMap, "Desc_Cor", ""));
		features.setSexo(getCsvRecordValueOrDefault(csvRecord, headerMap, "sexo", ""));
		features.setDescricaoCurso(getCsvRecordValueOrDefault(csvRecord, headerMap, "desc_curso", ""));
		features.setPercentualFrequencia(getCsvRecordValueOrDefault(csvRecord, headerMap, "Percentual_Frequencia", ""));
		features.setDescricaoTipoEscolaOrigem(
				getCsvRecordValueOrDefault(csvRecord, headerMap, "Desc_Tipo_Escola_Origem", ""));

		features.setDescricaoRendaPerCapita(
				getCsvRecordValueOrDefault(csvRecord, headerMap, "Desc_Renda_Per_Capita", ""));

		data.setFeatures(features);

		return data;
	}

	private String getCsvRecordValueOrDefault(CSVRecord csvRecord, Map<String, Integer> headerMap, String value,
			String def) {
		Integer valueIndex = headerMap.get(value);

		if (valueIndex == null) {
			return def;
		}

		return csvRecord.get(valueIndex) != null ? csvRecord.get(valueIndex) : def;
	}

}
