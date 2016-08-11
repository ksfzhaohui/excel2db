package org.excel2db.browse;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.excel2db.read.DBFile;
import org.excel2db.read.util.Constants;

/**
 * 浏览ndb文件的窗口
 * 
 * @author zhaohui
 * 
 */
public class NDBBrowseFrame extends JFrame {

	private final static Logger logger = Logger.getLogger(NDBBrowseFrame.class);
	private static final long serialVersionUID = 1L;

	private static final int frame_width = 800;
	private static final int frame_height = 500;
	private JTable table;
	private JScrollPane scrollPane;

	public NDBBrowseFrame() {
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);

		handlerDrag();
		this.setTitle("NDB Browser");
		this.setSize(frame_width, frame_height);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 定义的拖拽方法
	 */
	private void handlerDrag() {
		new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE,
				new DropTargetAdapter() {
					@Override
					public void drop(DropTargetDropEvent dtde) {
						try {
							if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
								dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
								@SuppressWarnings("unchecked")
								List<File> list = (List<File>) (dtde
										.getTransferable()
										.getTransferData(DataFlavor.javaFileListFlavor));

								browseNDB(list.get(0));
								dtde.dropComplete(true);
							} else {
								dtde.rejectDrop();
							}
						} catch (Exception e) {
							logger.error("", e);
						}
					}
				});
	}

	/**
	 * 浏览ndb中的数据
	 * 
	 * @param file
	 *            拖进来的ndb文件
	 */
	private void browseNDB(File file) {
		DBFile dbFile = new DBFile();
		dbFile.init(file.getAbsolutePath());

		List<Map<String, Object>> dataMap = dbFile.getDataMap();
		Object[] columnNames = dbFile.getColumnNames().toArray();
		Object[][] tableVales = new Object[dataMap.size()][columnNames.length];
		for (int i = 0; i < dataMap.size(); i++) {
			Map<String, Object> map = dataMap.get(i);
			Object tableValue[] = new Object[columnNames.length];
			for (int index = 0; index < columnNames.length; index++) {
				tableValue[index] = map.get(columnNames[index]);

				if (tableValue[index].getClass().isArray()) {
					StringBuffer sb = new StringBuffer();
					int len = Array.getLength(tableValue[index]);
					for (int j = 0; j < len; j++) {
						sb.append(Array.get(tableValue[index], j));
						if (j != len - 1) {
							sb.append(Constants.SEPARATOR);
						}
					}

					tableValue[index] = sb.toString();
				}
			}
			tableVales[i] = tableValue;
		}

		table.setModel(new NDBTableModel(columnNames, tableVales));
	}

	public static void main(String[] args) {
		new NDBBrowseFrame();
	}

}
