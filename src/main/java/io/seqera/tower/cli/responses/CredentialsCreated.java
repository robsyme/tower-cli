package io.seqera.tower.cli.responses;

public class CredentialsCreated extends Response {

    public final String id;
    public final String provider;
    public final String name;
    public final String workspaceRef;

    public CredentialsCreated(String provider, String id, String name, String workspaceRef) {
        this.provider = provider;
        this.id = id;
        this.name = name;
        this.workspaceRef = workspaceRef;
    }

    @Override
    public String toString() {
        return ansi(String.format("%n  @|yellow New %S credentials '%s (%s)' added at %s workspace|@%n", provider, name, id, workspaceRef));
    }
}