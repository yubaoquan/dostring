package package1.otherPeoplesWork;

import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;  
import java.beans.PropertyChangeEvent;  
import java.beans.PropertyChangeListener;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.PrintWriter;  
import java.util.List;  
  
import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.SwingWorker;  
  
public class ConsoleGUI extends JFrame {  
  
    private static final long serialVersionUID = 1L;  
  
    private CMDPane messagePane;  
  
    private Process process;  
    private PrintWriter outStream;  
    private InputStream inputStream;  
    private InputStream errorStream;  
    private InputWorker inputWorker = new InputWorker();  
    private ErrorWorker errorWorker = new ErrorWorker();  
  
    public ConsoleGUI() {  
        setSize(500, 500);  
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        setTitle("命令行GUI");  
  
        messagePane = new CMDPane();  
        messagePane.setForeground(Color.WHITE);  
        messagePane.setBackground(Color.BLACK);  
        add(messagePane, BorderLayout.CENTER);  
  
        // 启动一个CMD   
        try {  
            this.process = Runtime.getRuntime().exec("cmd");  
            inputStream = process.getInputStream();  
            errorStream = process.getErrorStream();  
            errorWorker.execute();  
            inputWorker.execute();  
            this.outStream = new PrintWriter(process.getOutputStream());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        // 命令框中按下回车键的事件   
        messagePane.addActionListener(new ActionListener() {  
  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                String cmd = messagePane.getCmd();  
                if (cmd == null || "".equals(cmd)) {  
                    return;  
                } else if ("cls".equals(cmd)) {  
                    messagePane.clearDate();  
                    messagePane.cleanCmd();  
                    return;  
                } else if ("exit".equals(cmd)) {  
                    dispose();  
                    return;  
                }  
                messagePane.addData(messagePane.getCmdTip() + cmd);  
                outStream.println(cmd);  
                outStream.flush();  
                messagePane.cleanCmd();  
            }  
        });  
        addWindowListener(new WindowAdapter() {  
            @Override  
            public void windowClosed(WindowEvent e) {  
                try {  
                    outStream.close();  
                    inputStream.close();  
                    errorStream.close();  
                    process.destroy();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        });  
        this.setLocationRelativeTo(null);  
        this.setVisible(true);  
        messagePane.cleanCmd();  
    } 
  
    public static void main(String[] args) {  
        System.setProperty("swing.defaultlaf",  
                "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");  
        SwingUtilities.invokeLater(new Runnable() {  
  
            @Override  
            public void run() {  
                new ConsoleGUI();  
            }  
        });  
    }  
  
    class ErrorWorker extends SwingWorker< String, String> {  
  
        @Override  
        protected void process(List< String> chunks) {  
            for (String string : chunks) {  
                messagePane.addData(string);  
            }  
        }  
  
        @Override  
        protected String doInBackground() throws Exception {  
            byte[] buf = new byte[1024];  
            int size;  
            while (true) {  
                if ((size = errorStream.read(buf)) != -1) {  
                    String org = new String(buf, 0, size, "gbk");  
                    String[] lines = org.split("\\n");  
                    if (lines != null) {  
                        for (int i = 0; i < lines.length; i++) {  
                            publish(lines[i]);  
                        }  
                    }  
                }  
            }  
        }  
  
    }  
  
    class InputWorker extends SwingWorker< String, String> {  
  
        public InputWorker() {  
            addPropertyChangeListener(new PropertyChangeListener() {  
  
                @Override  
                public void propertyChange(PropertyChangeEvent evt) {  
                    if ("cmd".equals(evt.getPropertyName())) {  
                        messagePane.setCmdTip(evt.getNewValue());  
                    }  
                }  
            });  
        }  
  
        @Override  
        protected void process(List< String> chunks) {  
            for (String string : chunks) {  
                messagePane.addData(string);  
            }  
        }  
  
        @Override  
        protected String doInBackground() throws Exception {  
            byte[] buf = new byte[1024];  
            int size;  
            while (true) {  
                if ((size = inputStream.read(buf)) != -1) {  
                    String org = new String(buf, 0, size, "gbk");  
                    String[] lines = org.split("\\n");  
                    if (lines != null && lines.length > 0) {  
                        for (int i = 0; i < lines.length - 1; i++) {  
                            //给予一定的延时，保证正常信息在错误信息之后显示   
                            Thread.sleep(10);  
                            publish(lines[i]);  
                        }  
                        firePropertyChange("cmd", null,  
                                lines[lines.length - 1]);  
                    }  
                }  
            }  
        }  
  
    }  
}  