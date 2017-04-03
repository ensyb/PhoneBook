package io.github.ensyb.phone.application.repository.queries;

public class PreparedStatementQuerieGenerator {

	public static String insert(String tableName, String... columnNames) {
		return new StringBuilder("INSERT INTO ").append(tableName).append(" (")
				.append(generateInsertColumnQuerryPart(columnNames)).toString();
	}

	public static String select(String tableName, String where) {
		return new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE ").append(where).append(" = ?;")
				.toString();
	}

	public static String selectWithOperator(String tableName, String where, String operator) {
		return new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE ").append(where).append(" ")
				.append(operator).append(" ?;").toString();
	}

	public static String selectWithOperatorAndOr(String tableName, String operator, String... conditions) {
		return new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE ")
				.append(generateConditionsForSelectWithAndOr(conditions, operator)).toString();
	}

	public static String insertUpdate(String tableName, String where, String... columnNames) {
		return new StringBuilder("UPDATE ").append(tableName).append(" SET ").append(generateSetInUpdate(columnNames))
				.append(" WHERE ").append(where).append(" = ?;").toString();
	}

	public static String delete(String tableName, String where) {
		return new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE ").append(where).append(" = ?;")
				.toString();
	}

	private static String generateInsertColumnQuerryPart(String[] columns) {
		StringBuilder builder = new StringBuilder();
		for (String column : columns)
			builder.append(column).append(", ");

		builder.deleteCharAt(builder.length() - 2);
		builder.append(") VALUES (");
		for (int i = 0; i < columns.length; i++)
			builder.append("?,");

		builder.deleteCharAt(builder.length() - 1);
		return builder.append(");").toString();
	}

	private static String generateConditionsForSelectWithAndOr(String[] conditions, String operator) {
		StringBuilder builder = new StringBuilder();
		for (String condition : conditions) {
			builder.append(" ").append(condition).append(" ").append(operator);
		}
		builder.delete(builder.length() - operator.length(), builder.length());
		builder.append(";");
		return builder.toString();
	}

	private static String generateSetInUpdate(String[] columnNames) {
		StringBuilder builder = new StringBuilder();
		for (String column : columnNames) {
			builder.append(column).append(" = ?,");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
}
