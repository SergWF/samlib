package my.wf.samlib.service.executive;

/**
 * Created by Serg on 14.01.2015.
 */
public class Help implements Executive {
    private String topic;

    public Help(String topic) {
        this.topic = topic;
    }

    @Override
    public void run() {
        System.out.println("HELP: " + topic);
    }
}
