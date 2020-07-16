import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color;
public class TicTacToe extends Applet implements ActionListener, ItemListener
{
	private Random rand = new Random();
	Checkbox user, cpu;
	CheckboxGroup cg;
	Button b[][] = new Button[3][3];;
	Button reset;
	Label l1;
	int m[][];
	Panel game, choice, menu;
	public void init()
	{
		setBackground(Color.GREEN);
		setForeground(Color.BLUE);
		game = new Panel();
		game.setLayout(new GridLayout(3,3));
		l1 = new Label("No Result");
		m = new int[3][3];
		for(int i = 0 ; i < 3 ; i++)
		{
			for(int j = 0 ; j < 3 ; j++)
			{
				b[i][j] = new Button("");
				m[i][j] = 0;
				b[i][j].setBackground(Color.black);
				//b[i][j].setForeground(Color.red);
				game.add(b[i][j]);
				b[i][j].addActionListener(this);
			}
		}
		cg = new CheckboxGroup();
		user = new Checkbox("User First",cg,true);
		cpu = new Checkbox("CPU First",cg,false);
		choice = new Panel();
		choice.setLayout(new GridLayout(1,2));
		choice.add(user);
		choice.add(cpu);
		user.addItemListener(this);
		cpu.addItemListener(this);
		menu = new Panel();
		reset = new Button("Reset");
		reset.setBackground(Color.yellow);
		reset.addActionListener(this);
		menu.setLayout(new GridLayout(3,1));
		menu.add(choice);
		menu.add(l1);
		menu.add(reset); 
		setLayout(new GridLayout(2,1));
		add(game);
		add(menu);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		user.setEnabled(false);
		cpu.setEnabled(false);
		if(source == reset)
			resetGame();
		else
		{
			for(int i = 0 ; i < 3 ; i++)
			{
				for(int j = 0 ; j < 3 ; j++)
				{
					if(source == b[i][j])
					{
						if(b[i][j].getLabel().equals(""))
						{
							b[i][j].setLabel("X");
							b[i][j].setEnabled(false);
							m[i][j] = 1;
							if(continueGame())
								cpuMove();
							break;
						}
					}
				}
			}
		}
	}
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource() == cpu)
		{
			resetGame();
			cpuMove();
			cpu.setState(true);
			user.setEnabled(false);
			cpu.setEnabled(false);
		}
	}
	public boolean continueGame()
	{
		int t = 0;
		for(int i = 0 ; i < 3 ; i++)
		{
			t = m[i][0] + m[i][1] + m[i][2];
			if (t == 3)
			{
				l1.setText("Congrats. You Won!");
				disableGame();
				return false;
			}
			else if (t == -3)
			{
				l1.setText("Sorry. You Loose!");
				disableGame();
				return false;
			}
		}
		for(int i = 0 ; i < 3 ; i++)
		{
			t = m[0][i] + m[1][i] + m[2][i];
			if (t == 3)
			{
				l1.setText("Congrats. You Won!");
				disableGame();
				return false;
			}
			else if (t == -3)
			{
				l1.setText("Sorry. You Loose!");
				disableGame();
				return false;
			}
		}
		t = m[0][0] + m[1][1] + m[2][2];
		if (t == 3)
		{
			l1.setText("Congrats. You Won!");
			disableGame();return false;
		}
		else if (t == -3)
		{
			l1.setText("Sorry. You Loose!");
			disableGame();
			return false;
		}
		t = m[0][2] + m[1][1] + m[2][0];
		if (t == 3)
		{
			l1.setText("Congrats. You Won!");
			disableGame();
			return false;
		}
		else if (t == -3)
		{
			l1.setText("Sorry. You Loose!");
			disableGame();
			return false;
		}
		return true;
		}
		public void cpuMove()
		{
			int x,y;
			while(true)
			{
				x = rand.nextInt(3);
				y = rand.nextInt(3);
				if(b[x][y].getLabel().equals(""))
				{
					b[x][y].setLabel("O");
					b[x][y].setEnabled(false);
					m[x][y] = -1;
					continueGame();
					break;
				}
			}
		}
		public void resetGame()
		{
			l1.setText("No Result");
			user.setEnabled(true);
			cpu.setEnabled(true);
			user.setState(true);
			for(int i = 0 ; i < 3 ; i++)
			{
				for(int j = 0 ; j < 3 ; j++)
				{
					b[i][j].setEnabled(true);
					b[i][j].setLabel("");
					m[i][j] = 0;
				}
			}
		}
		public void disableGame()
		{
			for(int i = 0 ; i < 3 ; i++)
			{
				for(int j = 0 ; j < 3 ; j++)
				{
					b[i][j].setEnabled(false);
				}
			}
		}
	}
	