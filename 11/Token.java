public final class Token {
	private String myTokenName = "";
	private String myTokenValue = "";

	public Token(String tokenName, String TokenValue) {
		myTokenName = tokenName;
		myTokenValue = TokenValue;
	}

	public String getTokenName() { return myTokenName; }
	public String getTokenValue() { return myTokenValue; }

	public boolean checkEqual(String tokenName, String tokenValue) {
		return myTokenName.equals(tokenName) && myTokenValue.equals(tokenValue);
	}

	public String toString() {
		return "Token name: " + myTokenName + ", Token Value: " + myTokenValue;
	}
}
