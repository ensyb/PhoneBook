package io.github.ensyb.phone.domain.contact.vo;

public class ContactVo {

	private final int id;
	private final int userId;
	private final String name;
	private final String phoneNumber;
	private final String description;

	public ContactVo(final int id, final int userId, final String name, final String phoneNumber,
			final String description) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.description = description;
	}

	public static class ContactVoBuilder {
		private int id;
		private int userId;
		private String name;
		private String phoneNumber;
		private String description;

		public ContactVoBuilder id(final int id) {
			this.id = id;
			return this;
		}

		public ContactVoBuilder userId(final int userId) {
			this.userId = userId;
			return this;
		}

		public ContactVoBuilder name(final String name) {
			this.name = name;
			return this;
		}

		public ContactVoBuilder phoneNumber(final String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public ContactVoBuilder description(final String description) {
			this.description = description;
			return this;
		}

		public ContactVo build() {
			return new ContactVo(this.id, this.userId, this.name, this.phoneNumber, this.description);
		}
	}

	public int id() {
		return this.id;
	}

	public int userId() {
		return this.userId;
	}

	public String name() {
		return this.name;
	}

	public String phoneNumber() {
		return this.phoneNumber;
	}

	public String description() {
		return this.description;
	}
	
	

}
