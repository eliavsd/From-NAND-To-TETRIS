import java.util.HashMap;

public final class SymbolTable {
	private static HashMap<String, String[]> CLASS_TABLE = new HashMap<>();
	private static HashMap<String, String[]> SUBROUTINE_TABLE = new HashMap<>();
	private static final String STATIC_STR = "static";
	private static final String FIELD_STR = "field";
	private static final String ARG_STR = "argument";
	private static final String VAR_STR = "variable";
	private static final int STATIC = 0;
	private static final int FIELD = 1;
	private static final int ARG = 2;
	private static final int VAR = 3;
	private static int[] KIND_COUNTS = {0, 0, 0, 0};

	private SymbolTable() {}

	private static int getIdx(String kind) {
		switch (kind) {
			case STATIC_STR:
				return STATIC;
			case FIELD_STR:
				return FIELD;
			case ARG_STR:
				return ARG;
			case VAR_STR:
				return VAR;
			default:
				return 17;
		}
	}

	public static void reset() {
		KIND_COUNTS[STATIC] = 0; KIND_COUNTS[FIELD] = 0; KIND_COUNTS[ARG] = 0; KIND_COUNTS[VAR] = 0;
		CLASS_TABLE.clear();
		SUBROUTINE_TABLE.clear();
	}

	public static void resetSubroutine() {
		SUBROUTINE_TABLE.clear();
		KIND_COUNTS[ARG] = 0;
		KIND_COUNTS[VAR] = 0;
	}

	public static void registerSym(String symName, String symType, String symKind) {
		String idx = Integer.toString(KIND_COUNTS[getIdx(symKind)]);
		++KIND_COUNTS[getIdx(symKind)];
		if (symKind.equals(STATIC_STR) || symKind.equals(FIELD_STR)) {
			CLASS_TABLE.put(symName, new String[] {symType, symKind, idx});
		}
		else if (symKind.equals(ARG_STR) || symKind.equals(VAR_STR)) {
			SUBROUTINE_TABLE.put(symName, new String[] {symType, symKind, idx});
		}
	}

	public static String[] find(String symName) {
		if (CLASS_TABLE.containsKey(symName)) {
			return CLASS_TABLE.get(symName);
		}
		else if (SUBROUTINE_TABLE.containsKey(symName)) {
			return SUBROUTINE_TABLE.get(symName);
		}
		else {
			return null;
		}
	}

	public static String getSymCount(String kind) { return Integer.toString(KIND_COUNTS[getIdx(kind)]); }

	public static String getType(String symName) {
		if (CLASS_TABLE.containsKey(symName)) {
			return CLASS_TABLE.get(symName)[0];
		}
		else if (SUBROUTINE_TABLE.containsKey(symName)) {
			return SUBROUTINE_TABLE.get(symName)[0];
		}
		else {
			return null;
		}
	}
}
