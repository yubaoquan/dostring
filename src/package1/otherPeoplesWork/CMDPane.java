package package1.otherPeoplesWork;

import java.awt.Color;  
import java.awt.Font;  
import java.awt.Graphics;  
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  
import java.awt.Insets;  
import java.awt.Rectangle;  
import java.awt.event.ActionListener;  
import java.awt.event.AdjustmentEvent;  
import java.awt.event.AdjustmentListener;  
import java.awt.event.ComponentAdapter;  
import java.awt.event.ComponentEvent;  
import java.util.LinkedList;  
  
import javax.swing.BorderFactory;  
import javax.swing.JComponent;  
import javax.swing.JScrollBar;  
import javax.swing.JTextField;  
import javax.swing.SwingUtilities;  
import javax.swing.UIManager;  
  
public class CMDPane extends JComponent {  
  
    /** 
     * 允许最大数据行数 
     */  
    private static final int MAXLINE = 10000;  
    /** 
     * 数据队列 
     */  
    private LinkedList< String> datas = new LinkedList< String>();  
    /** 
     * 每行数据的绘制高度 
     */  
    private int lineH = 1;  
    /** 
     * 滚动条 
     */  
    private JScrollBar bar = new JScrollBar(JScrollBar.VERTICAL);  
    /** 
     * 输入栏 
     */  
    private JTextField field = new JTextField();  
    /** 
     * 命令提示栏 
     */  
    private JTextField label = new JTextField(">");  
    /** 
     * 滚动条当前值 
     */  
    private int barValue = 0;  
    /** 
     * 滚动条更新任务 
     */  
    private Runnable barRunnable = new Runnable() {  
  
        @Override  
        public void run() {  
            bar.setValues(barValue, getHeight() / lineH, 0, datas.size() + 1);  
        }  
    };  
  
    /** 
     * 构造一个新的 MessagePane 
     */  
    public CMDPane() {  
        initialize();  
    }  
  
    /** 
     * 初始化 
     */  
    private void initialize() {  
        setOpaque(true);  
        setLayout(new GridBagLayout());  
        label.setOpaque(true);  
        field.setOpaque(true);  
        GridBagConstraints c = new GridBagConstraints();  
        c.gridx = 2;  
        c.gridy = 0;  
        c.gridheight = 2;  
        c.weighty = 1;  
        c.fill = GridBagConstraints.VERTICAL;  
        add(bar, c);  
        c.gridx = 0;  
        c.gridy = 1;  
        c.gridheight = 1;  
        c.weighty = 0;  
        c.fill = GridBagConstraints.HORIZONTAL;  
        c.anchor = GridBagConstraints.SOUTH;  
        add(label, c);  
        c.weightx = 1;  
        c.gridx = 1;  
        c.gridy = 1;  
        c.insets = new Insets(0, 0, 0, 0);  
        add(field, c);  
        field.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));  
        label.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 0));  
        label.setEditable(false);  
        bar.addAdjustmentListener(new AdjustmentListener() {  
  
            @Override  
            public void adjustmentValueChanged(AdjustmentEvent e) {  
                int newValue = bar.getValue();  
                if (newValue != barValue) {  
                    barValue = newValue;  
                    repaint();  
                }  
            }  
        });  
        addComponentListener(new ComponentAdapter() {  
  
            @Override  
            public void componentResized(ComponentEvent e) {  
                //控件大小变化时，更新滚动条   
                updateBar();  
            }  
        });  
        setFont(UIManager.getFont("Panel.font"));  
        lineH = getFontMetrics(getFont()).getHeight() + 2;  
        updateBar();  
    }  
  
    @Override  
    public void setBackground(Color bg) {  
        label.setBackground(bg);  
        field.setBackground(bg);  
        super.setBackground(bg);  
    }  
  
    @Override  
    public void setForeground(Color fg) {  
        label.setForeground(fg);  
        field.setForeground(fg);  
        super.setForeground(fg);  
    }  
  
    @Override  
    public void setFont(Font font) {  
        label.setFont(font);  
        field.setFont(font);  
        super.setFont(font);  
        if (font != null) {  
            lineH = getFontMetrics(font).getHeight() + 2;  
        }  
    }  
  
    /** 
     * 根据当前数据量，更新滚动条属性 
     */  
    private void updateBar() {  
        if (SwingUtilities.isEventDispatchThread()) {  
            barRunnable.run();  
        } else {  
            SwingUtilities.invokeLater(barRunnable);  
        }  
    }  
  
    /** 
     * 标准的添加数据接口，在事件指派线程中调用该方法，可以获得最大的性能优化 
     *  
     * @param data - 数据 
     */  
    public synchronized void addData(String data) {  
        datas.addLast(data);  
        if (datas.size() > MAXLINE) {  
            //数据超出限定范围时，移除最旧的一条数据   
            datas.pollFirst();  
        } else {  
            //数据长度变化时，更新滚动条   
            updateBar();  
        }  
        bar.setValue(datas.size());  
        repaint();  
    }  
  
    /** 
     * 标准的清除数据接口 
     */  
    public synchronized void clearDate() {  
        datas.clear();  
        //清除数据后，滚动条值归零   
        barValue = 0;  
        repaint();  
        //清除数据后，更新滚动条   
        updateBar();  
    }  
      
    /** 
     * 设置命题输入栏的提示 
     * @param text 
     */  
    public void setCmdTip(Object text) {  
        label.setText(text.toString());  
        revalidate();  
    }  
      
    /** 
     * 返回命题输入栏的提示 
     */  
    public String getCmdTip() {  
        return label.getText();  
    }  
      
    /** 
     * 获得命令 
     * @return 
     */  
    public String getCmd() {  
        return field.getText();  
    }  
      
    /** 
     * 清除命令 
     */  
    public void cleanCmd() {  
        field.setText("");  
        field.requestFocusInWindow();  
    }  
      
    /** 
     * 设置命令相应事件 
     * @param listener 
     */  
    public void addActionListener(ActionListener listener) {  
        field.addActionListener(listener);  
    }  
  
    /** 
     * 获得真实的绘制宽度（减去滚动条宽度） 
     *  
     * @return 
     */  
    private int getPaintWidth() {  
        return getWidth() - bar.getWidth();  
    }  
      
    /** 
     * 获得真实的绘制高度（减去输入框高度） 
     *  
     * @return 
     */  
    private int getPaintHeight() {  
        return getHeight() - field.getHeight();  
    }  
  
    @Override  
    protected void paintComponent(Graphics g) {  
        int w = getPaintWidth();  
        int h = getPaintHeight();  
        Rectangle clip = g.getClipBounds();  
        int clipX = clip.x;  
        int clipY = clip.y;  
        int clipH = clip.height;  
        int y = 0;  
        g.setColor(getBackground());  
        g.fillRect(clipX, clipY, w, clipH);  
        g.setColor(getForeground());  
        for (int i = barValue; i < datas.size(); i++) {  
            y += lineH;  
            if (y > clipY + h) {  
                //由于是从上往下绘制，因此大于绘制区域下沿时，终止绘制   
                break;  
            }  
            g.drawString(datas.get(i).toString(), 0, y);  
        }  
    }  
  
}  