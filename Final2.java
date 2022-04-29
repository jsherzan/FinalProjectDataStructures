import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Final2 extends JFrame{
	private JTable tableList;
	private JTable tableRead;
	private ReadOnlyTableModel listmodel;
	protected ReadOnlyTableModel readmodel;
	private String[] columns;
	private Object[][] rows;
	private JButton ratebtn;
	protected LinkedList<Book> bookList;
    private JRadioButton yesOption;
    private JRadioButton noOption;
	
	class ReadOnlyTableModel extends DefaultTableModel{ //makes it so text can't be edited 
		public ReadOnlyTableModel(Object[][] r, String[] cols) {
			super(r,cols); //calling base constructor
		}

		public boolean isCellEditable(int row, int col) { //set as false to make read only
			return false;
		}
	}
	
	public Final2() {
		setSize(1000,500); //setting size 
		setTitle("Rate books");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InitData();
		AddBookList();
		AddRatedList();
		AddRateButton();
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Final2();
	}

	private void InitData() { //Queue adding books
		bookList = new LinkedList<Book>();

		bookList.add(new Book("00001","Harry Potter"));
		bookList.add(new Book("00002","Left Drowning"));
		bookList.add(new Book("00003","The Midnight Library"));
		bookList.add(new Book("00004","Dune"));
		bookList.add(new Book("00005","Strangers We Know"));
		bookList.add(new Book("00006","I Hate You Don't Leave Me"));
		bookList.add(new Book("00007","Klara and the Sun"));
		bookList.add(new Book("00008","How to Stop Time"));
		bookList.add(new Book("00009","My Friend Leonard"));
		bookList.add(new Book("000010","A Million Little Pieces"));
		bookList.add(new Book("000011","Beautiful Broken Rules"));
		bookList.add(new Book("000012","The Stand"));
		bookList.add(new Book("000013","Children of Dune"));
		bookList.add(new Book("000014","Beast Charming"));
		bookList.add(new Book("000015","Gus"));
	}
	
	private void AddRateButton() { //adding lower 2 radio buttons & 1 button
		ratebtn = new JButton("Mark as read and rate Book");
		ratebtn.addActionListener(new ActionListener() 
	      {
	         @Override
	         public void actionPerformed(ActionEvent ae) 
	         {
	            if(tableList.getSelectedRow() != -1) //what happens onclick event
	            {
	            	int rowIndex = tableList.getSelectedRow();
	            	String isbn = String.valueOf(listmodel.getValueAt(rowIndex, 0));
	            	String title = String.valueOf(listmodel.getValueAt(rowIndex, 1));
	            	RatedBook readBook = new RatedBook(isbn, title, yesOption.isSelected());
	            	
	            	listmodel.removeRow(rowIndex);
	            	Object[] row = new Object[]{
	            			readBook.getIsbn(), 
	            			readBook.getTitle(), 
	            			readBook.getRecommend() ? "Yes" : "No"};
	            	readmodel.addRow(row);
	            	JOptionPane.showMessageDialog(null, "Thank you for rating the book.");
	            }
	            else {
	            	JOptionPane.showMessageDialog(null, "Please select a book from the list.");
	            }
	         }
	      });
	       yesOption = new JRadioButton("Recommend Book");
	       noOption = new JRadioButton("Do Not Recommend Book");
	       ButtonGroup bGroup = new ButtonGroup();
	       noOption.setSelected(true);
	       bGroup.add(yesOption);
	       bGroup.add(noOption);
	       JPanel panel = new JPanel();
	       panel.add(yesOption, BorderLayout.NORTH);
	       panel.add(noOption, BorderLayout.CENTER);
	       panel.add(ratebtn, BorderLayout.SOUTH);
		add(panel, BorderLayout.SOUTH);
	}

	private void AddBookList() {
		JPanel panel = new JPanel();
	      panel.setBorder(BorderFactory.createTitledBorder(
	      BorderFactory.createEtchedBorder(), "Available books", TitledBorder.LEFT,
	      TitledBorder.TOP));
	      
	      columns = new String[] {"isbn", "Title"}; //looping through book list to add to UI control
	      Object[][] rows =  new Object[bookList.size()][];
	      for(int i = 0; i < bookList.size(); i++) {
	    	   Book current = (Book) bookList.get(i);
	    	   rows[i] =  new Object[]{current.getIsbn(), current.getTitle()};
	      }		

	      listmodel = new ReadOnlyTableModel(rows, columns);
	      tableList = new JTable(listmodel);
	      tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	      add( new JScrollPane(tableList), BorderLayout.WEST);
	}

	private void AddRatedList() { //adding UI control for books that have been rated
		JPanel panel = new JPanel();
	      panel.setBorder(BorderFactory.createTitledBorder(
	      BorderFactory.createEtchedBorder(), "Read books", TitledBorder.LEFT,
	      TitledBorder.TOP));

	      String[] cols = new String[] {"isbn", "Title", "Recommend"};
		Object[][] r = new Object[][] {
	      };
	      readmodel = new ReadOnlyTableModel(r, cols);
	      tableRead = new JTable(readmodel);
	      tableRead.setRowSelectionAllowed(false);
	      tableRead.setCellSelectionEnabled(false);
	      add( new JScrollPane(tableRead), BorderLayout.EAST);
	}

}
