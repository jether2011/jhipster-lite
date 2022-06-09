package tech.jhipster.lite.generator.module.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.module.domain.javadependency.ArtifactId;
import tech.jhipster.lite.generator.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.generator.module.domain.javadependency.GroupId;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependencies;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependency;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependencyScope;
import tech.jhipster.lite.generator.module.domain.javadependency.JavaDependencyVersion;
import tech.jhipster.lite.generator.module.domain.javadependency.ProjectJavaDependencies;
import tech.jhipster.lite.generator.module.domain.javadependency.VersionSlug;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterProjectFolder;

@UnitTest
class FileSystemProjectJavaDependenciesRepositoryTest {

  private static FileSystemProjectJavaDependenciesRepository projectDependencies = new FileSystemProjectJavaDependenciesRepository();

  @Test
  void shouldNotReadFromUnreadableMavenFile() {
    assertThatThrownBy(() -> projectDependencies.get(new JHipsterProjectFolder("src/test/resources/projects/maven-unreadable")))
      .isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldGetEmptyDependenciesFromEmptyProject() {
    assertThat(projectDependencies.get(new JHipsterProjectFolder("src/test/resources/projects/empty")))
      .isEqualTo(ProjectJavaDependencies.EMPTY);
  }

  @Test
  void shouldGetEmptyProjectDependenciesFromEmptyMavenFile() {
    assertThat(projectDependencies.get(new JHipsterProjectFolder("src/test/resources/projects/empty-maven")))
      .usingRecursiveComparison()
      .isEqualTo(ProjectJavaDependencies.EMPTY);
  }

  @Test
  void shouldGetVersionsFromMavenFile() {
    JavaDependenciesVersions versions = mavenDependencies().versions();

    assertThat(versions.get(new VersionSlug("jjwt"))).contains(new JavaDependencyVersion("jjwt", "0.11.5"));
    assertThat(versions.get(new VersionSlug("logstash-logback-encoder.version")))
      .contains(new JavaDependencyVersion("logstash-logback-encoder", "7.2"));
    assertThat(versions.get(new VersionSlug("dummy"))).isEmpty();
  }

  @Test
  void shouldGetDependenciesFromMavenFile() {
    JavaDependencies dependencies = mavenDependencies().dependencies();

    assertJJWTDependency(dependencies);
    assertLogstashDependency(dependencies);
    assertThat(dependencies.get(new DependencyId(new GroupId("org.springdoc"), new ArtifactId("springdoc-openapi-ui")))).isEmpty();
  }

  private void assertJJWTDependency(JavaDependencies dependencies) {
    JavaDependency jjwt = dependencies.get(new DependencyId(new GroupId("io.jsonwebtoken"), new ArtifactId("jjwt-api"))).get();
    assertThat(jjwt.version()).contains(new VersionSlug("jjwt"));
    assertThat(jjwt.scope()).isEqualTo(JavaDependencyScope.TEST);
    assertThat(jjwt.optional()).isTrue();
  }

  private void assertLogstashDependency(JavaDependencies dependencies) {
    JavaDependency jjwt = dependencies
      .get(new DependencyId(new GroupId("net.logstash.logback"), new ArtifactId("logstash-logback-encoder")))
      .get();
    assertThat(jjwt.version()).isEmpty();
    assertThat(jjwt.scope()).isEqualTo(JavaDependencyScope.COMPILE);
    assertThat(jjwt.optional()).isFalse();
  }

  private ProjectJavaDependencies mavenDependencies() {
    return projectDependencies.get(new JHipsterProjectFolder("src/test/resources/projects/maven"));
  }
}
