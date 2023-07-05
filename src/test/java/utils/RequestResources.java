package utils;

public enum RequestResources {

    projectsPath("/api/v4/projects/");
    private String resource;

    RequestResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
