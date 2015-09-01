grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.dependency.resolver="maven"

grails.project.dependency.resolution = {

    inherits("global") {
        excludes 'xml-apis', 'netty', 'logback-classic' //, 'xercesImpl'
    }
    log "warn"
    checksums true
    legacyResolve false

    repositories {
        inherits true

        mavenLocal()
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        mavenRepo "http://m2.neo4j.org/content/repositories/releases/"

        //mavenRepo 'http://m2.neo4j.org/releases'
        //mavenRepo "http://repo.grails.org/grails/repo"
    }

    dependencies {

        def datastoreVersion = "3.1.6.BUILD-SNAPSHOT"
        def neo4jDatastoreVersion = "2.0.0-SNAPSHOT"
//        def neo4jDatastoreVersion = "2.0.0-M02"
        def seleniumVersion = "2.40.0"
        def excludes = {
            excludes "slf4j-simple", "persistence-api", "commons-logging", "jcl-over-slf4j", "slf4j-api", "jta"
            excludes "spring-core", "spring-beans", "spring-aop", "spring-asm","spring-webmvc","spring-tx", "spring-context", "spring-web", "log4j", "slf4j-log4j12"
            excludes group:"org.grails", name:'grails-core'
            excludes group:"org.grails", name:'grails-gorm'
            excludes group:"org.grails", name:'grails-test'
            excludes group:'xml-apis', name:'xml-apis'
            excludes 'ehcache-core'
        }

        compile ("org.grails:grails-datastore-gorm-neo4j:$neo4jDatastoreVersion",excludes)
        compile("org.grails:grails-datastore-gorm-plugin-support:$datastoreVersion",
                "org.grails:grails-datastore-gorm:$datastoreVersion",
                "org.grails:grails-datastore-core:$datastoreVersion",
                "org.grails:grails-datastore-simple:$datastoreVersion",
                "org.grails:grails-datastore-web:$datastoreVersion",excludes)        

        compile('org.neo4j:neo4j-community:2.0.3')

        compile "org.neo4j:neo4j-jdbc:2.0.2"
        runtime 'org.apache.httpcomponents:httpcore:4.3'
        runtime 'org.apache.httpcomponents:httpclient:4.3.1'
        runtime 'org.apache.httpcomponents:httpmime:4.3.1'


        test "org.gebish:geb-spock:0.9.2"
        test "org.seleniumhq.selenium:selenium-support:$seleniumVersion"
        test( "com.github.detro.ghostdriver:phantomjsdriver:1.1.0" ) {
           transitive = false
        }
        test "org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion"
        test "org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion"
       // test "org.spockframework:spock-grails-support:0.7-groovy-2.0"

        // htmlunit seems to be broken
/*        test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") {
            exclude 'xml-apis'
        }*/
    }

    plugins {
        runtime(":jquery:1.11.0.1", ":resources:1.2.7") {
            export = false
        }
        build(":tomcat:7.0.50") {
            export = false
        }
        build(":release:3.0.1",  ":rest-client-builder:1.0.3") {
            export = false
        }
/*        test(":spock:0.7", ":geb:0.9.2") {
            export = false
            exclude "spock-grails-support"
        }
*/
    }

}
