package First;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Gui extends JFrame implements ActionListener {
	private JTextArea text2;
	private JButton  start;
	private JButton  exit;
	private JButton  show;
	private JTextField text;
	private Label l;
	private Thread t;
	private String dir;

	public Gui(Thread t,String dir) {
		setLayout(new BorderLayout(5, 5));
		this.t = t;
		this.dir = dir;
		start = new JButton ("Start");
		exit = new JButton ("Exit");
		show = new JButton ("Show");
		text2 = new JTextArea();
		start.addActionListener(this);
		exit.addActionListener(this);
		show.addActionListener(this);
		l = new Label("                                                                                          Information", SwingConstants.CENTER);
		l.setLocation((this.getWidth()-l.getWidth())/2,50);
		add(start,BorderLayout.WEST);
		add(exit,BorderLayout.EAST);
		add(show,BorderLayout.SOUTH);
		add(l,BorderLayout.NORTH);
		add(text2,BorderLayout.CENTER);
		setTitle("Controller");
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });
		start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               t.start();
            }
        });
		show.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              File f = new File(dir);
              try {
				String info = printStat(f);
				text2.setText(info);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            }
        });
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	private static String printStat(File file) throws FileNotFoundException, IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        int lines = 0;
        StringBuilder builder = new StringBuilder();
        long length = file.length();
        length--;
        String s = "";
        randomAccessFile.seek(length);
        for(long seek = length; seek >= 0; --seek){
            randomAccessFile.seek(seek);
            char c = (char)randomAccessFile.read();
            builder.append(c);
            if(c == '\n'){
                builder = builder.reverse();
                //System.out.println(builder.toString());
                s +=builder.toString();
                lines++;
                builder = null;
                builder = new StringBuilder();
                if (lines == 10){
                    break;
                }
            }

        }
        return s;
    }

}
