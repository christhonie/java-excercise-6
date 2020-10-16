package za.co.idealogic.moviemanager.config.properties;

interface SwaggerDefaults {

    String title = "Application API";
    String description = "API documentation";
    String version = "0.0.1";
    String termsOfServiceUrl = null;
    String contactName = null;
    String contactUrl = null;
    String contactEmail = null;
    String license = null;
    String licenseUrl = null;
    String defaultIncludePattern = "/api/.*";
    String host = "http://localhost:8080";
    String[] protocols = {};
    boolean useDefaultResponseMessages = true;
} 
