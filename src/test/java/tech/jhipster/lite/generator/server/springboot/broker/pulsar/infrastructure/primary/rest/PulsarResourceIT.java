package tech.jhipster.lite.generator.server.springboot.broker.pulsar.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.tmpDirForTest;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_DOCKER;
import static tech.jhipster.lite.generator.server.springboot.broker.pulsar.domain.Pulsar.PULSAR_DOCKER_COMPOSE_FILE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.broker.pulsar.application.PulsarApplicationService;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
@AutoConfigureMockMvc
class PulsarResourceIT {

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Autowired
  PulsarApplicationService pulsarApplicationService;

  @Autowired
  DockerService dockerService;

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldInitPulsar() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(tmpDirForTest());
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    Project project = ProjectDTO.toProject(projectDTO);
    initApplicationService.init(project);
    mavenApplicationService.init(project);
    springBootApplicationService.init(project);

    mockMvc
      .perform(
        post("/api/servers/spring-boot/brokers/pulsar")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    String projectPath = projectDTO.getFolder();
    assertFileExist(projectPath, MAIN_DOCKER + "/" + PULSAR_DOCKER_COMPOSE_FILE);
    assertFileExist(projectPath, "src/main/java/tech/jhipster/chips/technical/infrastructure/config/pulsar/PulsarProperties.java");
    assertFileExist(projectPath, "src/main/java/tech/jhipster/chips/technical/infrastructure/config/pulsar/PulsarConfiguration.java");
    assertFileExist(projectPath, "src/test/java/tech/jhipster/chips/technical/infrastructure/config/pulsar/PulsarConfigurationIT.java");
    assertFileExist(projectPath, "src/test/java/tech/jhipster/chips/PulsarTestContainerExtension.java");
  }
}
