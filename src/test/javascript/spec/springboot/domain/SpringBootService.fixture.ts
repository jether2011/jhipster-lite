import { SpringBootService } from '@/springboot/domain/SpringBootService';
import sinon, { SinonStub } from 'sinon';

export interface SpringBootServiceFixture extends SpringBootService {
  addSpringBoot: SinonStub;

  addSpringBootMvcTomcat: SinonStub;
  addSpringBootWebfluxNetty: SinonStub;
  addSpringBootActuator: SinonStub;
  addSpringDoc: SinonStub;

  addSpringBootAopLogging: SinonStub;
  addSpringBootLogstash: SinonStub;

  addEhcacheWithJavaConf: SinonStub;
  addEhcacheWithXML: SinonStub;
  addSimpleCache: SinonStub;
  addSpringCloudConfigClient: SinonStub;
  addSpringCloudConsul: SinonStub;
  addSpringCloudEureka: SinonStub;
  addSpringBootAsync: SinonStub;
  addSpringBootDevtoolsDependencies: SinonStub;
  addSpringBootDockerfile: SinonStub;
  addSpringBootDockerJib: SinonStub;
  addSpringBootBannerIppon: SinonStub;
  addSpringBootBannerJHipsterV2: SinonStub;
  addSpringBootBannerJHipsterV3: SinonStub;
  addSpringBootBannerJHipsterV7: SinonStub;
  addSpringBootBannerJHipsterV7React: SinonStub;
  addSpringBootBannerJHipsterV7Vue: SinonStub;
  addPostgres: SinonStub;
  addMySQL: SinonStub;
  addMariaDB: SinonStub;
  addMongoDB: SinonStub;
  addSpringBootFlywayInit: SinonStub;
  addSpringBootFlywayUser: SinonStub;
  addSpringBootLiquibaseInit: SinonStub;
  addSpringBootLiquibaseUser: SinonStub;
  addSpringBootMongockInit: SinonStub;
  addJWT: SinonStub;
  addBasicAuthJWT: SinonStub;
  addOAuth2: SinonStub;
  addOAuth2Account: SinonStub;
  addSpringdocJWT: SinonStub;

  addPulsar: SinonStub;
  addCucumber: SinonStub;
}

export const stubSpringBootService = (): SpringBootServiceFixture => ({
  addSpringBoot: sinon.stub(),

  addSpringBootMvcTomcat: sinon.stub(),
  addSpringBootWebfluxNetty: sinon.stub(),
  addSpringBootActuator: sinon.stub(),
  addSpringDoc: sinon.stub(),

  addSpringBootAopLogging: sinon.stub(),
  addSpringBootLogstash: sinon.stub(),

  addEhcacheWithJavaConf: sinon.stub(),
  addEhcacheWithXML: sinon.stub(),
  addSimpleCache: sinon.stub(),
  addSpringCloudConfigClient: sinon.stub(),
  addSpringCloudConsul: sinon.stub(),
  addSpringCloudEureka: sinon.stub(),
  addSpringBootAsync: sinon.stub(),
  addSpringBootDevtoolsDependencies: sinon.stub(),
  addSpringBootDockerfile: sinon.stub(),
  addSpringBootDockerJib: sinon.stub(),
  addSpringBootBannerIppon: sinon.stub(),
  addSpringBootBannerJHipsterV2: sinon.stub(),
  addSpringBootBannerJHipsterV3: sinon.stub(),
  addSpringBootBannerJHipsterV7: sinon.stub(),
  addSpringBootBannerJHipsterV7React: sinon.stub(),
  addSpringBootBannerJHipsterV7Vue: sinon.stub(),
  addPostgres: sinon.stub(),
  addMySQL: sinon.stub(),
  addMariaDB: sinon.stub(),
  addMongoDB: sinon.stub(),
  addSpringBootFlywayInit: sinon.stub(),
  addSpringBootFlywayUser: sinon.stub(),
  addSpringBootLiquibaseInit: sinon.stub(),
  addSpringBootLiquibaseUser: sinon.stub(),
  addSpringBootMongockInit: sinon.stub(),
  addJWT: sinon.stub(),
  addBasicAuthJWT: sinon.stub(),
  addOAuth2: sinon.stub(),
  addOAuth2Account: sinon.stub(),
  addSpringdocJWT: sinon.stub(),

  addPulsar: sinon.stub(),
  addCucumber: sinon.stub(),
});
