package tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain;

import static tech.jhipster.lite.common.domain.FileUtils.*;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommon.testContainersDependency;
import static tech.jhipster.lite.generator.server.springboot.database.sqlcommon.domain.SQLCommon.*;

import java.util.Map;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.DefaultConfig;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.Level;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class SQLCommonDomainService implements SQLCommonService {

  public static final String PROJECT = "project";
  public static final String DATABASE = "database";
  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;
  private final ProjectRepository projectRepository;

  public SQLCommonDomainService(
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService,
    ProjectRepository projectRepository
  ) {
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
    this.projectRepository = projectRepository;
  }

  @Override
  public void addTestcontainers(Project project, String database, Map<String, Object> properties) {
    Assert.notNull(PROJECT, project);
    Assert.notBlank(DATABASE, database);

    buildToolService.addVersionPropertyAndDependency(project, "testcontainers", testContainersDependency(database));
    springBootCommonService.addPropertiesTestComment(project, "Database Configuration");
    properties.forEach((k, v) -> springBootCommonService.addPropertiesTest(project, k, v));
    springBootCommonService.addPropertiesTestNewLine(project);
  }

  @Override
  public void addSpringDataJpa(Project project) {
    Assert.notNull(PROJECT, project);

    buildToolService.addDependency(project, SQLCommon.springDataJpa());
  }

  @Override
  public void addHikari(Project project) {
    Assert.notNull(PROJECT, project);

    buildToolService.addDependency(project, SQLCommon.hikariDependency());
  }

  @Override
  public void addHibernateCore(Project project) {
    Assert.notNull(PROJECT, project);

    buildToolService.addDependency(project, SQLCommon.sqlHibernateCore());
  }

  @Override
  public void addDockerComposeTemplate(Project project, String database) {
    Assert.notNull(PROJECT, project);
    Assert.notBlank(DATABASE, database);

    final String ymlFileName = database + ".yml";
    projectRepository.template(
      ProjectFile.forProject(project).withSource(getSource(database), ymlFileName).withDestination("src/main/docker", ymlFileName)
    );
  }

  @Override
  public void addJavaFiles(Project project, String database) {
    Assert.notNull(PROJECT, project);

    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(BASE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath(DefaultConfig.PACKAGE_PATH));
    String databasePath = getDatabasePath(database);

    projectRepository.template(
      ProjectFile
        .forProject(project)
        .withSource(getSource(database), "DatabaseConfiguration.java")
        .withDestinationFolder(getPath(MAIN_JAVA, packageNamePath, databasePath))
    );
  }

  @Override
  public void addProperties(Project project, Map<String, Object> properties) {
    Assert.notNull(PROJECT, project);
    Assert.notNull("properties", properties);

    Map<String, Object> commonProperties = SQLCommon.springProperties();

    springBootCommonService.addPropertiesComment(project, "Database Configuration");
    commonProperties.putAll(properties);

    commonProperties.forEach((k, v) -> springBootCommonService.addProperties(project, k, v));
    springBootCommonService.addPropertiesNewLine(project);
  }

  @Override
  public void addLoggers(Project project) {
    Assert.notNull(PROJECT, project);

    SQLCommon.loggers().forEach((k, v) -> addLogger(project, k, v));
  }

  private void addLogger(Project project, String packageName, Level level) {
    springBootCommonService.addLogger(project, packageName, level);
    springBootCommonService.addLoggerTest(project, packageName, level);
  }
}
