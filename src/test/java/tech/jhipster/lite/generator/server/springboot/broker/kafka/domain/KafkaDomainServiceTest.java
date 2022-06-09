package tech.jhipster.lite.generator.server.springboot.broker.kafka.domain;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static tech.jhipster.lite.TestUtils.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.readme.domain.ReadMeService;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

@UnitTest
@ExtendWith(MockitoExtension.class)
class KafkaDomainServiceTest {

  @Mock
  BuildToolService buildToolService;

  @Mock
  ProjectRepository projectRepository;

  @Mock
  SpringBootCommonService springBootCommonService;

  @Mock
  DockerService dockerService;

  @Mock
  ReadMeService readMeService;

  @InjectMocks
  KafkaDomainService kafkaDomainService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPomXml();
    when(dockerService.getImageNameWithVersion(anyString())).thenReturn(Optional.of("dummy"));

    kafkaDomainService.init(project);

    verify(buildToolService, times(1)).addDependency(eq(project), any(Dependency.class));
    verify(buildToolService, times(1)).addVersionPropertyAndDependency(eq(project), eq("testcontainers"), any(Dependency.class));
    verify(dockerService, times(2)).getImageNameWithVersion(anyString());
    verify(projectRepository, times(5)).template(any(ProjectFile.class));
    verify(springBootCommonService, times(9)).addProperties(eq(project), anyString(), any());
    verify(springBootCommonService, times(9)).addPropertiesTest(eq(project), anyString(), any());
    verify(springBootCommonService).updateIntegrationTestAnnotation(eq(project), anyString());
    verify(readMeService).addSection(eq(project), anyString(), anyString());
  }

  @Test
  void shouldAddProducerConsumer() {
    Project project = tmpProjectWithPomXml();

    when(springBootCommonService.getProperty(eq(project), anyString())).thenReturn(Optional.empty());
    kafkaDomainService.addDummyProducerConsumer(project);

    verify(springBootCommonService).addProperties(eq(project), anyString(), any());
    verify(springBootCommonService).addPropertiesTest(eq(project), anyString(), any());
    verify(projectRepository, times(7)).template(any(ProjectFile.class));
  }

  @Test
  void shouldAddAkhq() {
    Project project = tmpProjectWithPomXml();
    when(dockerService.getImageNameWithVersion(anyString())).thenReturn(Optional.of("dummy"));

    kafkaDomainService.addAkhq(project);

    verify(dockerService).getImageNameWithVersion(anyString());
    verify(projectRepository).template(any(ProjectFile.class));
  }

  @Test
  void shouldNotAddProducerConsumer() {
    Project project = tmpProjectWithPomXml();

    when(springBootCommonService.getProperty(eq(project), anyString())).thenReturn(Optional.of("queue.jhipster.dummy"));
    kafkaDomainService.addDummyProducerConsumer(project);

    verify(springBootCommonService).getProperty(eq(project), anyString());
  }
}
