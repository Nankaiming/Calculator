import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MyFrame extends JFrame {
	private JLabel rst;
	private String exp = "";
	private int result = 0;
	

	// ¹¹Ôìº¯Êý
	public MyFrame() {
		JPanel mainPanel = new JPanel();
		JPanel result = new JPanel();
		JPanel keyboard = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		keyboard.setLayout(new GridLayout(4, 4));
		JButton number0 = new JButton("0");
		JButton number1 = new JButton("1");
		JButton number2 = new JButton("2");
		JButton number3 = new JButton("3");
		JButton number4 = new JButton("4");
		JButton number5 = new JButton("5");
		JButton number6 = new JButton("6");
		JButton number7 = new JButton("7");
		JButton number8 = new JButton("8");
		JButton number9 = new JButton("9");
		JButton multi = new JButton("*");
		JButton divide = new JButton("/");
		JButton add = new JButton("+");
		JButton substract = new JButton("-");
		JButton equal = new JButton("=");
		JButton clear = new JButton("CLR");
		keyboard.add(number7);
		keyboard.add(number8);
		keyboard.add(number9);
		keyboard.add(add);
		keyboard.add(number4);
		keyboard.add(number5);
		keyboard.add(number6);
		keyboard.add(substract);
		keyboard.add(number1);
		keyboard.add(number2);
		keyboard.add(number3);
		keyboard.add(multi);
		keyboard.add(number0);
		keyboard.add(clear);
		keyboard.add(equal);
		keyboard.add(divide);
		number0.addActionListener(new numberListener());
		number1.addActionListener(new numberListener());
		number2.addActionListener(new numberListener());
		number3.addActionListener(new numberListener());
		number4.addActionListener(new numberListener());
		number5.addActionListener(new numberListener());
		number6.addActionListener(new numberListener());
		number7.addActionListener(new numberListener());
		number8.addActionListener(new numberListener());
		number9.addActionListener(new numberListener());
		multi.addActionListener(new operatorListener());
		divide.addActionListener(new operatorListener());
		add.addActionListener(new operatorListener());
		substract.addActionListener(new operatorListener());
		clear.addActionListener(new cleanListener());
		equal.addActionListener(new resultListener());
		result.setLayout(new GridLayout(1, 1));
		rst = new JLabel();
		rst.setText("0");
		rst.setHorizontalAlignment(JLabel.RIGHT);
		result.setBorder(new LineBorder(Color.black));
		result.add(rst);
		result.setBackground(Color.white);
		mainPanel.add(result, BorderLayout.NORTH);
		mainPanel.add(keyboard, BorderLayout.CENTER);
		this.setTitle("Calculator");
		this.add(mainPanel);
		this.setResizable(false);
		this.setSize(300, 180);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ²Ù×÷·û°´Å¥¼àÌý
	class operatorListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton source = (JButton) e.getSource();
			char operator = source.getText().charAt(0);
			switch (operator) {
			case '+':
				if (exp != "") {
					if (exp.endsWith("+")) {

					} else if (exp.endsWith("-") || exp.endsWith("*")
							|| exp.endsWith("/")) {
						exp = exp.substring(0, exp.length() - 1) + "+";
					} else {
						exp = exp + "+";
					}
				} else {
					exp = "0" + "+";
				}
				break;
			case '-':
				if (exp != "") {
					if (exp.endsWith("-")) {

					} else if (exp.endsWith("+")) {
						exp = exp.substring(0, exp.length() - 1) + "-";
					} else {
						exp = exp + "-";
					}
				} else {
					exp = "0" + "-";
				}
				break;
			case '*':
				if (exp != "") {
					if (exp.endsWith("+") || exp.endsWith("-")
							|| exp.endsWith("*") || exp.endsWith("/")) {
						exp = exp.substring(0, exp.length() - 1) + "*";
					} else {
						exp = exp + "*";
					}
				} else {
					exp = "0" + "+";
				}
				break;
			case '/':
				if (exp != "") {
					if (exp.endsWith("+") || exp.endsWith("-")
							|| exp.endsWith("*") || exp.endsWith("/")) {
						exp = exp.substring(0, exp.length() - 1) + "/";
					} else {
						exp = exp + "/";
					}
				} else {
					exp = "0" + "+";
				}
				break;

			default:
				break;
			}
			rst.setText(exp);
		}

	}

	// CLR°´Å¥¼àÌý
	class cleanListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			exp = "";
			rst.setText("0");
		}

	}

	// Êý×Ö°´Å¥¼àÌý
	class numberListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton source = (JButton) e.getSource();
			exp = exp + source.getText();
			rst.setText(exp);
		}

	}

	// = °´Å¥¼àÌý
	class resultListener implements ActionListener {

		int flag;
		ArrayList<String> list;
		private String rss;

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			System.out.println(exp);
			if(exp == ""){
				rst.setText("0");
				return ;
			}
			if(exp.startsWith("NaN")){
				rst.setText("NaN");
				return ;
			}else if(exp.startsWith("da")){
				rst.setText("da");
				return ;
			}else if(exp.startsWith("xiao")){
				rst.setText("xiao");
				return ;
			}
			if (exp.endsWith("*") || exp.endsWith("/") || exp.endsWith("+")
					|| exp.endsWith("-")) {
				exp = exp.substring(0, exp.length() - 1);
			} else if (exp.endsWith("*+") || exp.endsWith("/+")
					|| exp.endsWith("*-") || exp.endsWith("/-")) {
				exp = exp.substring(0, exp.length() - 2);
			}
			String[] operator = exp.split("[0-9]+");
//			for (String string : operator) {
//				System.out.println(string);
//			}
			if (operator.length == 0) {
				rst.setText(exp);
				return ;
			} else {
				String[] operat = new String[operator.length - 1];
				for (int i = 1; i < operator.length; i++) {
					operat[i - 1] = operator[i];
				}
				Pattern pattern = Pattern.compile("[0-9]+");
				Matcher matcher = pattern.matcher(exp);
				list = new ArrayList<String>();
				while (matcher.find()) {
					list.add(matcher.group());
				}
//				System.out.println(list);
				String[] number = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					if (i == 0) {
						number[i] = list.get(i);
					} else {
						if (operator[i - 1].equals("*-")) {
							number[i] = "-" + list.get(i);
							operator[i - 1] = "*";
						} else if (operator[i - 1].equals("/-")) {
							number[i] = "-" + list.get(i);
							operator[i - 1] = "/";
						} else {
							number[i] = list.get(i);
						}
					}
				}
//				for (String string : number) {
//					System.out.println(string);
//				}
//				for (String string : operat) {
//					System.out.println(string);
//				}
				rss = calculate(operat, number);
				rst.setText(rss);
			}
			exp = rss;
		}
	}

	public static String calculate(String[] operator, String[] number) {
		int res = 0;
		String rs = "";
		Stack<String> opeStack = new Stack<String>();
		Stack<String> numStack = new Stack<String>();
		numStack.push(number[0]);
		for (int i = 1; i < number.length; i++) {

			opeStack.push(operator[i - 1]);
			numStack.push(number[i]);
			String s = opeStack.peek();
			if (s.equals("*")) {
				String b = numStack.pop();
				String a = numStack.pop();
				String oper = opeStack.pop();
				res = Integer.parseInt(a) * (Integer.parseInt(b));
				numStack.push(String.valueOf(res));
			}
			if (s.equals("/")) {
				String b = numStack.pop();
				String a = numStack.pop();
				String oper = opeStack.pop();
				try {
					res = Integer.parseInt(a) / Integer.parseInt(b);
					numStack.push(String.valueOf(res));
				} catch (Exception e) {
					// TODO: handle exception
					if(Integer.parseInt(a) > 0){
						rs = "da";
					}else if(Integer.parseInt(a) == 0){
						rs = "NaN";
					}else if(Integer.parseInt(a)  < 0){
						rs = "xiao";
					}
					return rs;
				}
			}

		}
		while (!opeStack.empty()) {
			String b = numStack.pop();
			String a = numStack.pop();
			String oper = opeStack.pop();
			if (oper.equals("+")) {
				res = Integer.parseInt(a) + Integer.parseInt(b);
			}
			if (oper.equals("-")) {
				res = Integer.parseInt(a) - Integer.parseInt(b);
			}
			numStack.push(String.valueOf(res));
		}
		rs = numStack.pop();
		return rs;
	}

}
