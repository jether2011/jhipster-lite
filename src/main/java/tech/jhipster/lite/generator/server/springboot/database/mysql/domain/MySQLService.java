package tech.jhipster.lite.generator.server.springboot.database.mysql.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface MySQLService {
  void init(Project project);

  void addSpringDataJpa(Project project);
  void addMySQLDriver(Project project);
  void addHikari(Project project);
  void addHibernateCore(Project project);
  void addDockerCompose(Project project);
  void addJavaFiles(Project project);
  void addProperties(Project project);
  void addLoggerInConfiguration(Project project);
}
