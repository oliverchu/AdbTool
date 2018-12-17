package top.iofox.lib.adb.core;

public abstract class Base {

    private String baseCommand = "";

    public Base() {

    }

    public Base(String command) {
        this.baseCommand = command;
    }

    public Base(Base base) {
        this.baseCommand = base.getBaseCommand() + " " + onCreateCommand();
    }

    private void appendSubCommand() {
        this.baseCommand = this.baseCommand + " " + onCreateCommand();
    }

    public String getBaseCommand() {
        return baseCommand;
    }

    public Command newCommand() {
        return new Command(baseCommand);
    }

    public abstract String onCreateCommand();

    static class Command {
        private StringBuilder builder;

        public Command(String command) {
            builder = new StringBuilder(command).append(" ");
        }


        public Command append(Object sub) {
            builder.append(sub).append(" ");
            return this;
        }

        public String string() {
            return builder.toString();
        }
    }

}
