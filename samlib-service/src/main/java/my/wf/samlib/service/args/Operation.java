package my.wf.samlib.service.args;

import my.wf.samlib.service.executive.*;


public enum Operation {
    HELP {
        @Override
        public Executive getExecutive(String[] args) {
            String topic = args.length >1?args[1]:"all";
            return new Help(topic);
        }
    },BACKUP {
        @Override
        public Executive getExecutive(String[] args) {
            return new Backup();
        }
    }, RESTORE {
        @Override
        public Executive getExecutive(String[] args) {
            return new Restore();
        }
    },RUN {
        @Override
        public Executive getExecutive(String[] args) {
            return new Runner();
        }
    };

    public abstract Executive getExecutive(String[] args);
}
