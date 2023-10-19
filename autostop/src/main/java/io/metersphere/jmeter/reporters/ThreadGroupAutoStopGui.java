package io.metersphere.jmeter.reporters;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import kg.apc.jmeter.JMeterPluginsUtils;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.visualizers.gui.AbstractListenerGui;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ThreadGroupAutoStopGui extends AbstractListenerGui {

    private static final Logger log = LoggerFactory.getLogger(ThreadGroupAutoStopGui.class);
    public static final String WIKIPAGE = "AutoStop";
    private JThreadGroupAutoStopPanel autoStopPanel;

    public ThreadGroupAutoStopGui() {
        super();
        init();
        autoStopPanel.initFields();
    }

    @Override
    public String getStaticLabel() {
        return "MeterSphere - AutoStop Listener";
    }

    @Override
    public String getLabelResource() {
        return getClass().getCanonicalName();
    }

    @Override
    public TestElement createTestElement() {
        TestElement te = new ThreadGroupAutoStop();
        modifyTestElement(te);
        te.setComment(JMeterPluginsUtils.getWikiLinkText(WIKIPAGE));
        return te;
    }

    @Override
    public void modifyTestElement(TestElement te) {
        super.configureTestElement(te);
        if (te instanceof ThreadGroupAutoStop) {
            ThreadGroupAutoStop fw = (ThreadGroupAutoStop) te;
            autoStopPanel.modifyTestElement(fw);
        }
    }

    @Override
    public void clearGui() {
        super.clearGui();
        autoStopPanel.initFields();
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof ThreadGroupAutoStop) {
            ThreadGroupAutoStop fw = (ThreadGroupAutoStop) element;
            autoStopPanel.configure(fw);
        }
    }

    private void init() {
        autoStopPanel = new JThreadGroupAutoStopPanel();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());

        JPanel container = new JPanel(new BorderLayout());
        container.add(autoStopPanel, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);
    }
}
