package ua.lviv.iot.model.entity.formatter;

import java.util.LinkedList;
import java.util.List;

public class Formatter<T> {
	public Formatter() {

	}

	public void formatTable(List<T> rows) {
		String[] columnNames = rows.get(0).toString().split("\n")[0].split("\\s+\\|\\s+");
		String[][] formatedTable = new String[rows.size() + 1][columnNames.length];
		for (int column = 0; column < columnNames.length; column++) {
			List<String> columnValues = new LinkedList<>();
			for (int row = 0; row < rows.size(); row++) {
				String[] values = rows.get(row).toString().split("\n")[2].split("\\s+\\|\\s+");
				columnValues.add(values[column]);
			}
			int maxLengthInColumn = findMaxInColumn(columnNames[column],
					columnValues.toArray(new String[columnValues.size()]));
			formatedTable[0][column] = String.format("%-" + maxLengthInColumn + "s", columnNames[column]) + "   | ";
			for (int row = 0; row < rows.size(); row++) {
				formatedTable[row + 1][column] = String.format("%-" + maxLengthInColumn + "s", columnValues.get(row))
						+ "   | ";
			}
		}
		for (int i = 0; i < rows.size() + 1; i++) {
			for (int j = 0; j < columnNames.length; j++) {
				System.out.print(formatedTable[i][j]);
			}
			System.out.println();
		}
	}

	private int findMaxInColumn(String columnName, String[] columnValues) {
		int maxLength = columnValues[0].length();
		for (int counter = 1; counter < columnValues.length; counter++) {
			if (columnValues[counter].length() > maxLength) {
				maxLength = columnValues[counter].length();
			}
		}
		maxLength = columnName.length() > maxLength ? columnName.length() : maxLength;
		return maxLength;
	}

	public static String formatRow(String[] columnNames, String[] columnValues) {
		String formattedColumnNames = "";
		String formattedColumnValues = "";
		for (int counter = 0; counter < columnNames.length; counter++) {
			int length = columnNames[counter].length() > columnValues[counter].length() ? columnNames[counter].length()
					: columnValues[counter].length();
			formattedColumnNames += String.format("%-" + length + "s", columnNames[counter]) + "   |   ";
			formattedColumnValues += String.format("%-" + length + "s", columnValues[counter]) + "   |   ";
		}
		return formattedColumnNames + "\n\n" + formattedColumnValues;
	}
}
