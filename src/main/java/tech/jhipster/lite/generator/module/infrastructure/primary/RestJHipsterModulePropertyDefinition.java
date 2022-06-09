package tech.jhipster.lite.generator.module.infrastructure.primary;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModulePropertyDefinition;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterPropertyDescription;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterPropertyExample;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterPropertyType;

@Schema(name = "JHipsterModulePropertyDefinition", description = "Definition for a module property")
class RestJHipsterModulePropertyDefinition {

  private final JHipsterPropertyType type;
  private final boolean mandatory;
  private final String key;
  private final String description;
  private final String example;

  private RestJHipsterModulePropertyDefinition(RestJHipsterModulePropertyDefinitionBuilder builder) {
    type = builder.type;
    mandatory = builder.mandatory;
    key = builder.key;
    description = builder.description;
    example = builder.example;
  }

  static RestJHipsterModulePropertyDefinition from(JHipsterModulePropertyDefinition propertyDefinition) {
    return new RestJHipsterModulePropertyDefinitionBuilder()
      .type(propertyDefinition.type())
      .mandatory(propertyDefinition.isMandatory())
      .key(propertyDefinition.key().get())
      .description(propertyDefinition.description().map(JHipsterPropertyDescription::get).orElse(null))
      .example(propertyDefinition.example().map(JHipsterPropertyExample::get).orElse(null))
      .build();
  }

  @Schema(description = "Type of this property", required = true)
  public JHipsterPropertyType getType() {
    return type;
  }

  @Schema(description = "True if the field is mandatory, false otherwise", required = true)
  public boolean isMandatory() {
    return mandatory;
  }

  @Schema(description = "Key of this property", required = true)
  public String getKey() {
    return key;
  }

  @Schema(description = "Description of this property")
  public String getDescription() {
    return description;
  }

  @Schema(description = "Example value for this property")
  public String getExample() {
    return example;
  }

  private static class RestJHipsterModulePropertyDefinitionBuilder {

    private JHipsterPropertyType type;
    private boolean mandatory;
    private String key;
    private String description;
    private String example;

    public RestJHipsterModulePropertyDefinitionBuilder type(JHipsterPropertyType type) {
      this.type = type;

      return this;
    }

    public RestJHipsterModulePropertyDefinitionBuilder mandatory(boolean mandatory) {
      this.mandatory = mandatory;

      return this;
    }

    public RestJHipsterModulePropertyDefinitionBuilder key(String key) {
      this.key = key;

      return this;
    }

    public RestJHipsterModulePropertyDefinitionBuilder description(String description) {
      this.description = description;

      return this;
    }

    public RestJHipsterModulePropertyDefinitionBuilder example(String example) {
      this.example = example;

      return this;
    }

    public RestJHipsterModulePropertyDefinition build() {
      return new RestJHipsterModulePropertyDefinition(this);
    }
  }
}
