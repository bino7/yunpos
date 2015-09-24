// Generated from D:/workspace/yunpos/src/main/java/com/yunpos/rewriter/antlr\MySQL.g4 by ANTLR 4.5.1
package com.yunpos.rewriter.antlr;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MySQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SELECT=1, DELETE=2, UPDATE=3, INSERT=4, INTO=5, VALUES=6, FROM=7, SET=8, 
		WHERE=9, AS=10, AND=11, OR=12, XOR=13, IS=14, NULL=15, LIKE=16, IN=17, 
		EXISTS=18, ALL=19, ANY=20, TRUE=21, FALSE=22, DIVIDE=23, MOD=24, BETWEEN=25, 
		REGEXP=26, PLUS=27, MINUS=28, NEGATION=29, VERTBAR=30, BITAND=31, POWER=32, 
		BINARY=33, SHIFT_LEFT=34, SHIFT_RIGHT=35, ESCAPE=36, ASTERISK=37, RPAREN=38, 
		LPAREN=39, RBRACK=40, LBRACK=41, COLON=42, EQ=43, LTH=44, GTH=45, NOT_EQ=46, 
		NOT=47, LET=48, GET=49, SEMI=50, COMMA=51, DOT=52, COLLATE=53, INNER=54, 
		OUTER=55, JOIN=56, CROSS=57, USING=58, INDEX=59, KEY=60, ORDER=61, GROUP=62, 
		BY=63, FOR=64, USE=65, IGNORE=66, PARTITION=67, STRAIGHT_JOIN=68, NATURAL=69, 
		LEFT=70, RIGHT=71, OJ=72, ON=73, LIMIT=74, ASC=75, DESC=76, HAVING=77, 
		OFFSET=78, DEFAULT=79, QUTE=80, LODASH=81, ID=82, INT=83, NEWLINE=84, 
		WS=85, USER_VAR=86;
	public static final int
		RULE_expr = 0, RULE_term = 1, RULE_atom = 2, RULE_quoted_variable = 3, 
		RULE_number = 4, RULE_neg_number = 5, RULE_function = 6, RULE_function_name = 7, 
		RULE_stat = 8, RULE_schema_name = 9, RULE_select_clause = 10, RULE_delect_clause = 11, 
		RULE_insert_clause = 12, RULE_update_clause = 13, RULE_table_name = 14, 
		RULE_table_alias = 15, RULE_value = 16, RULE_values_list_clause = 17, 
		RULE_column = 18, RULE_column_name = 19, RULE_all_coumn_name = 20, RULE_column_alias = 21, 
		RULE_index_name = 22, RULE_column_list = 23, RULE_column_list_clause = 24, 
		RULE_from_clause = 25, RULE_where_clause = 26, RULE_expression_clause = 27, 
		RULE_expression = 28, RULE_quoted_expression = 29, RULE_right_element = 30, 
		RULE_left_element = 31, RULE_target_element = 32, RULE_relational_op = 33, 
		RULE_expr_op = 34, RULE_between_op = 35, RULE_is_or_is_not = 36, RULE_simple_expression = 37, 
		RULE_table_references = 38, RULE_table_reference = 39, RULE_table_factor1 = 40, 
		RULE_table_factor2 = 41, RULE_table_factor3 = 42, RULE_table_factor4 = 43, 
		RULE_table_atom = 44, RULE_join_clause = 45, RULE_join_condition = 46, 
		RULE_index_hint_list = 47, RULE_index_options = 48, RULE_index_hint = 49, 
		RULE_index_list = 50, RULE_partition_clause = 51, RULE_partition_names = 52, 
		RULE_partition_name = 53, RULE_subquery_alias = 54, RULE_subquery = 55, 
		RULE_offset = 56, RULE_row_count = 57;
	public static final String[] ruleNames = {
		"expr", "term", "atom", "quoted_variable", "number", "neg_number", "function", 
		"function_name", "stat", "schema_name", "select_clause", "delect_clause", 
		"insert_clause", "update_clause", "table_name", "table_alias", "value", 
		"values_list_clause", "column", "column_name", "all_coumn_name", "column_alias", 
		"index_name", "column_list", "column_list_clause", "from_clause", "where_clause", 
		"expression_clause", "expression", "quoted_expression", "right_element", 
		"left_element", "target_element", "relational_op", "expr_op", "between_op", 
		"is_or_is_not", "simple_expression", "table_references", "table_reference", 
		"table_factor1", "table_factor2", "table_factor3", "table_factor4", "table_atom", 
		"join_clause", "join_condition", "index_hint_list", "index_options", "index_hint", 
		"index_list", "partition_clause", "partition_names", "partition_name", 
		"subquery_alias", "subquery", "offset", "row_count"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'select'", "'delete'", "'update'", "'insert'", "'into'", "'values'", 
		"'from'", "'set'", "'where'", "'as'", null, null, "'xor'", "'is'", "'null'", 
		"'like'", "'in'", "'exists'", "'all'", "'any'", "'true'", "'false'", null, 
		null, "'between'", "'regexp'", "'+'", "'-'", "'~'", "'|'", "'&'", "'^'", 
		"'binary'", "'<<'", "'>>'", "'escape'", "'*'", "')'", "'('", "']'", "'['", 
		"':'", "'='", "'<'", "'>'", "'!='", "'not'", "'<='", "'>='", "';'", "','", 
		"'.'", "'collate'", "'inner'", "'outer'", "'join'", "'cross'", "'using'", 
		"'index'", "'key'", "'order'", "'group'", "'by'", "'for'", "'use'", "'ignore'", 
		"'partition'", "'straight_join'", "'natural'", "'left'", "'right'", "'oj'", 
		"'on'", "'limit'", "'asc'", "'desc'", "'having'", "'offset'", "'default'", 
		"'''", "'_'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SELECT", "DELETE", "UPDATE", "INSERT", "INTO", "VALUES", "FROM", 
		"SET", "WHERE", "AS", "AND", "OR", "XOR", "IS", "NULL", "LIKE", "IN", 
		"EXISTS", "ALL", "ANY", "TRUE", "FALSE", "DIVIDE", "MOD", "BETWEEN", "REGEXP", 
		"PLUS", "MINUS", "NEGATION", "VERTBAR", "BITAND", "POWER", "BINARY", "SHIFT_LEFT", 
		"SHIFT_RIGHT", "ESCAPE", "ASTERISK", "RPAREN", "LPAREN", "RBRACK", "LBRACK", 
		"COLON", "EQ", "LTH", "GTH", "NOT_EQ", "NOT", "LET", "GET", "SEMI", "COMMA", 
		"DOT", "COLLATE", "INNER", "OUTER", "JOIN", "CROSS", "USING", "INDEX", 
		"KEY", "ORDER", "GROUP", "BY", "FOR", "USE", "IGNORE", "PARTITION", "STRAIGHT_JOIN", 
		"NATURAL", "LEFT", "RIGHT", "OJ", "ON", "LIMIT", "ASC", "DESC", "HAVING", 
		"OFFSET", "DEFAULT", "QUTE", "LODASH", "ID", "INT", "NEWLINE", "WS", "USER_VAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MySQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MySQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExprContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			term(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Token operator;
		public TerminalNode LPAREN() { return getToken(MySQLParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(MySQLParser.RPAREN, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public TerminalNode POWER() { return getToken(MySQLParser.POWER, 0); }
		public TerminalNode DIVIDE() { return getToken(MySQLParser.DIVIDE, 0); }
		public TerminalNode ASTERISK() { return getToken(MySQLParser.ASTERISK, 0); }
		public TerminalNode PLUS() { return getToken(MySQLParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MySQLParser.MINUS, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_term, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(119);
				match(LPAREN);
				setState(120);
				term(0);
				setState(121);
				match(RPAREN);
				}
				break;
			case 2:
				{
				setState(123);
				atom();
				}
				break;
			case 3:
				{
				setState(124);
				function();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(141);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(139);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(127);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(128);
						((TermContext)_localctx).operator = match(POWER);
						setState(129);
						term(4);
						}
						break;
					case 2:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(130);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(131);
						((TermContext)_localctx).operator = match(DIVIDE);
						setState(132);
						term(4);
						}
						break;
					case 3:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(133);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(134);
						((TermContext)_localctx).operator = match(ASTERISK);
						setState(135);
						term(3);
						}
						break;
					case 4:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(136);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(137);
						((TermContext)_localctx).operator = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((TermContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(138);
						term(2);
						}
						break;
					}
					} 
				}
				setState(143);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Neg_numberContext neg_number() {
			return getRuleContext(Neg_numberContext.class,0);
		}
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public TerminalNode USER_VAR() { return getToken(MySQLParser.USER_VAR, 0); }
		public Quoted_variableContext quoted_variable() {
			return getRuleContext(Quoted_variableContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atom);
		try {
			setState(149);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				neg_number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				column_name();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				match(USER_VAR);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(148);
				quoted_variable();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Quoted_variableContext extends ParserRuleContext {
		public List<TerminalNode> QUTE() { return getTokens(MySQLParser.QUTE); }
		public TerminalNode QUTE(int i) {
			return getToken(MySQLParser.QUTE, i);
		}
		public TerminalNode ID() { return getToken(MySQLParser.ID, 0); }
		public Quoted_variableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quoted_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterQuoted_variable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitQuoted_variable(this);
		}
	}

	public final Quoted_variableContext quoted_variable() throws RecognitionException {
		Quoted_variableContext _localctx = new Quoted_variableContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_quoted_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(QUTE);
			setState(152);
			match(ID);
			setState(153);
			match(QUTE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(MySQLParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(MySQLParser.INT, i);
		}
		public TerminalNode DOT() { return getToken(MySQLParser.DOT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(INT);
			setState(158);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(156);
				match(DOT);
				setState(157);
				match(INT);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Neg_numberContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(MySQLParser.LPAREN, 0); }
		public TerminalNode MINUS() { return getToken(MySQLParser.MINUS, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MySQLParser.RPAREN, 0); }
		public Neg_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_neg_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterNeg_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitNeg_number(this);
		}
	}

	public final Neg_numberContext neg_number() throws RecognitionException {
		Neg_numberContext _localctx = new Neg_numberContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_neg_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(LPAREN);
			setState(161);
			match(MINUS);
			setState(162);
			number();
			setState(163);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(MySQLParser.LPAREN, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(MySQLParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			function_name();
			setState(166);
			match(LPAREN);
			setState(167);
			term(0);
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(168);
				match(COMMA);
				setState(169);
				term(0);
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(175);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MySQLParser.ID, 0); }
		public Function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterFunction_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitFunction_name(this);
		}
	}

	public final Function_nameContext function_name() throws RecognitionException {
		Function_nameContext _localctx = new Function_nameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public Select_clauseContext select_clause() {
			return getRuleContext(Select_clauseContext.class,0);
		}
		public Delect_clauseContext delect_clause() {
			return getRuleContext(Delect_clauseContext.class,0);
		}
		public Insert_clauseContext insert_clause() {
			return getRuleContext(Insert_clauseContext.class,0);
		}
		public Update_clauseContext update_clause() {
			return getRuleContext(Update_clauseContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitStat(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stat);
		try {
			setState(183);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				select_clause();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				delect_clause();
				}
				break;
			case INSERT:
			case SET:
				enterOuterAlt(_localctx, 3);
				{
				setState(181);
				insert_clause();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(182);
				update_clause();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Schema_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MySQLParser.ID, 0); }
		public Schema_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterSchema_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitSchema_name(this);
		}
	}

	public final Schema_nameContext schema_name() throws RecognitionException {
		Schema_nameContext _localctx = new Schema_nameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_schema_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_clauseContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(MySQLParser.SELECT, 0); }
		public List<Column_list_clauseContext> column_list_clause() {
			return getRuleContexts(Column_list_clauseContext.class);
		}
		public Column_list_clauseContext column_list_clause(int i) {
			return getRuleContext(Column_list_clauseContext.class,i);
		}
		public TerminalNode FROM() { return getToken(MySQLParser.FROM, 0); }
		public Table_referencesContext table_references() {
			return getRuleContext(Table_referencesContext.class,0);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(MySQLParser.GROUP, 0); }
		public List<TerminalNode> BY() { return getTokens(MySQLParser.BY); }
		public TerminalNode BY(int i) {
			return getToken(MySQLParser.BY, i);
		}
		public TerminalNode ORDER() { return getToken(MySQLParser.ORDER, 0); }
		public TerminalNode LIMIT() { return getToken(MySQLParser.LIMIT, 0); }
		public Row_countContext row_count() {
			return getRuleContext(Row_countContext.class,0);
		}
		public TerminalNode OFFSET() { return getToken(MySQLParser.OFFSET, 0); }
		public OffsetContext offset() {
			return getRuleContext(OffsetContext.class,0);
		}
		public TerminalNode HAVING() { return getToken(MySQLParser.HAVING, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> ASC() { return getTokens(MySQLParser.ASC); }
		public TerminalNode ASC(int i) {
			return getToken(MySQLParser.ASC, i);
		}
		public List<TerminalNode> DESC() { return getTokens(MySQLParser.DESC); }
		public TerminalNode DESC(int i) {
			return getToken(MySQLParser.DESC, i);
		}
		public Select_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterSelect_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitSelect_clause(this);
		}
	}

	public final Select_clauseContext select_clause() throws RecognitionException {
		Select_clauseContext _localctx = new Select_clauseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_select_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(SELECT);
			setState(188);
			column_list_clause();
			setState(191);
			_la = _input.LA(1);
			if (_la==FROM) {
				{
				setState(189);
				match(FROM);
				setState(190);
				table_references();
				}
			}

			setState(194);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(193);
				where_clause();
				}
			}

			setState(206);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(196);
				match(GROUP);
				setState(197);
				match(BY);
				setState(198);
				column_list_clause();
				setState(200);
				_la = _input.LA(1);
				if (_la==ASC || _la==DESC) {
					{
					setState(199);
					_la = _input.LA(1);
					if ( !(_la==ASC || _la==DESC) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				setState(204);
				_la = _input.LA(1);
				if (_la==HAVING) {
					{
					setState(202);
					match(HAVING);
					setState(203);
					expression();
					}
				}

				}
			}

			setState(214);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(208);
				match(ORDER);
				setState(209);
				match(BY);
				setState(210);
				column_list_clause();
				setState(212);
				_la = _input.LA(1);
				if (_la==ASC || _la==DESC) {
					{
					setState(211);
					_la = _input.LA(1);
					if ( !(_la==ASC || _la==DESC) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				}
			}

			setState(225);
			switch (_input.LA(1)) {
			case LIMIT:
				{
				setState(216);
				match(LIMIT);
				setState(218);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(217);
					offset();
					}
					break;
				}
				setState(220);
				row_count();
				}
				break;
			case INT:
				{
				setState(221);
				row_count();
				setState(222);
				match(OFFSET);
				setState(223);
				offset();
				}
				break;
			case EOF:
			case RPAREN:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delect_clauseContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(MySQLParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(MySQLParser.FROM, 0); }
		public Table_referencesContext table_references() {
			return getRuleContext(Table_referencesContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(MySQLParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ORDER() { return getToken(MySQLParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(MySQLParser.BY, 0); }
		public Column_list_clauseContext column_list_clause() {
			return getRuleContext(Column_list_clauseContext.class,0);
		}
		public TerminalNode LIMIT() { return getToken(MySQLParser.LIMIT, 0); }
		public Row_countContext row_count() {
			return getRuleContext(Row_countContext.class,0);
		}
		public TerminalNode OFFSET() { return getToken(MySQLParser.OFFSET, 0); }
		public OffsetContext offset() {
			return getRuleContext(OffsetContext.class,0);
		}
		public TerminalNode ASC() { return getToken(MySQLParser.ASC, 0); }
		public TerminalNode DESC() { return getToken(MySQLParser.DESC, 0); }
		public Delect_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delect_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterDelect_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitDelect_clause(this);
		}
	}

	public final Delect_clauseContext delect_clause() throws RecognitionException {
		Delect_clauseContext _localctx = new Delect_clauseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_delect_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(DELETE);
			setState(228);
			match(FROM);
			setState(229);
			table_references();
			setState(232);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(230);
				match(WHERE);
				setState(231);
				expression();
				}
			}

			setState(240);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(234);
				match(ORDER);
				setState(235);
				match(BY);
				setState(236);
				column_list_clause();
				setState(238);
				_la = _input.LA(1);
				if (_la==ASC || _la==DESC) {
					{
					setState(237);
					_la = _input.LA(1);
					if ( !(_la==ASC || _la==DESC) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				}
			}

			setState(251);
			switch (_input.LA(1)) {
			case LIMIT:
				{
				setState(242);
				match(LIMIT);
				setState(244);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(243);
					offset();
					}
					break;
				}
				setState(246);
				row_count();
				}
				break;
			case INT:
				{
				setState(247);
				row_count();
				setState(248);
				match(OFFSET);
				setState(249);
				offset();
				}
				break;
			case EOF:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Insert_clauseContext extends ParserRuleContext {
		public TerminalNode INSERT() { return getToken(MySQLParser.INSERT, 0); }
		public Table_referencesContext table_references() {
			return getRuleContext(Table_referencesContext.class,0);
		}
		public TerminalNode VALUES() { return getToken(MySQLParser.VALUES, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(MySQLParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(MySQLParser.LPAREN, i);
		}
		public Values_list_clauseContext values_list_clause() {
			return getRuleContext(Values_list_clauseContext.class,0);
		}
		public List<TerminalNode> RPAREN() { return getTokens(MySQLParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(MySQLParser.RPAREN, i);
		}
		public TerminalNode INTO() { return getToken(MySQLParser.INTO, 0); }
		public Column_list_clauseContext column_list_clause() {
			return getRuleContext(Column_list_clauseContext.class,0);
		}
		public TerminalNode SET() { return getToken(MySQLParser.SET, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(MySQLParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(MySQLParser.EQ, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public Insert_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterInsert_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitInsert_clause(this);
		}
	}

	public final Insert_clauseContext insert_clause() throws RecognitionException {
		Insert_clauseContext _localctx = new Insert_clauseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_insert_clause);
		int _la;
		try {
			setState(278);
			switch (_input.LA(1)) {
			case INSERT:
				enterOuterAlt(_localctx, 1);
				{
				setState(253);
				match(INSERT);
				setState(255);
				_la = _input.LA(1);
				if (_la==INTO) {
					{
					setState(254);
					match(INTO);
					}
				}

				setState(257);
				table_references();
				{
				setState(262);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(258);
					match(LPAREN);
					setState(259);
					column_list_clause();
					setState(260);
					match(RPAREN);
					}
				}

				setState(264);
				match(VALUES);
				setState(265);
				match(LPAREN);
				setState(266);
				values_list_clause();
				setState(267);
				match(RPAREN);
				}
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(269);
				match(SET);
				setState(274); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(270);
					column_name();
					setState(271);
					match(EQ);
					setState(272);
					value();
					}
					}
					setState(276); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AS || _la==ASTERISK || _la==ID || _la==USER_VAR );
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_clauseContext extends ParserRuleContext {
		public TerminalNode UPDATE() { return getToken(MySQLParser.UPDATE, 0); }
		public Table_referencesContext table_references() {
			return getRuleContext(Table_referencesContext.class,0);
		}
		public TerminalNode SET() { return getToken(MySQLParser.SET, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(MySQLParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(MySQLParser.EQ, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> DEFAULT() { return getTokens(MySQLParser.DEFAULT); }
		public TerminalNode DEFAULT(int i) {
			return getToken(MySQLParser.DEFAULT, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public TerminalNode WHERE() { return getToken(MySQLParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ORDER() { return getToken(MySQLParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(MySQLParser.BY, 0); }
		public Column_list_clauseContext column_list_clause() {
			return getRuleContext(Column_list_clauseContext.class,0);
		}
		public TerminalNode LIMIT() { return getToken(MySQLParser.LIMIT, 0); }
		public Row_countContext row_count() {
			return getRuleContext(Row_countContext.class,0);
		}
		public TerminalNode OFFSET() { return getToken(MySQLParser.OFFSET, 0); }
		public OffsetContext offset() {
			return getRuleContext(OffsetContext.class,0);
		}
		public TerminalNode ASC() { return getToken(MySQLParser.ASC, 0); }
		public TerminalNode DESC() { return getToken(MySQLParser.DESC, 0); }
		public Update_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterUpdate_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitUpdate_clause(this);
		}
	}

	public final Update_clauseContext update_clause() throws RecognitionException {
		Update_clauseContext _localctx = new Update_clauseContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_update_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(UPDATE);
			setState(281);
			table_references();
			setState(282);
			match(SET);
			setState(283);
			column_name();
			setState(284);
			match(EQ);
			setState(287);
			switch (_input.LA(1)) {
			case AS:
			case ASTERISK:
			case LPAREN:
			case QUTE:
			case ID:
			case INT:
			case USER_VAR:
				{
				setState(285);
				expr();
				}
				break;
			case DEFAULT:
				{
				setState(286);
				match(DEFAULT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(289);
				match(COMMA);
				setState(290);
				column_name();
				setState(291);
				match(EQ);
				setState(294);
				switch (_input.LA(1)) {
				case AS:
				case ASTERISK:
				case LPAREN:
				case QUTE:
				case ID:
				case INT:
				case USER_VAR:
					{
					setState(292);
					expr();
					}
					break;
				case DEFAULT:
					{
					setState(293);
					match(DEFAULT);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(303);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(301);
				match(WHERE);
				setState(302);
				expression();
				}
			}

			setState(311);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(305);
				match(ORDER);
				setState(306);
				match(BY);
				setState(307);
				column_list_clause();
				setState(309);
				_la = _input.LA(1);
				if (_la==ASC || _la==DESC) {
					{
					setState(308);
					_la = _input.LA(1);
					if ( !(_la==ASC || _la==DESC) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				}
			}

			setState(322);
			switch (_input.LA(1)) {
			case LIMIT:
				{
				setState(313);
				match(LIMIT);
				setState(315);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(314);
					offset();
					}
					break;
				}
				setState(317);
				row_count();
				}
				break;
			case INT:
				{
				setState(318);
				row_count();
				setState(319);
				match(OFFSET);
				setState(320);
				offset();
				}
				break;
			case EOF:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MySQLParser.ID, 0); }
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTable_name(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			match(ID);
			setState(326);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(325);
				table_alias();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_aliasContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MySQLParser.ID, 0); }
		public TerminalNode AS() { return getToken(MySQLParser.AS, 0); }
		public Table_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTable_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTable_alias(this);
		}
	}

	public final Table_aliasContext table_alias() throws RecognitionException {
		Table_aliasContext _localctx = new Table_aliasContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_table_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(328);
				match(AS);
				}
			}

			setState(331);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Values_list_clauseContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public Values_list_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_values_list_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterValues_list_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitValues_list_clause(this);
		}
	}

	public final Values_list_clauseContext values_list_clause() throws RecognitionException {
		Values_list_clauseContext _localctx = new Values_list_clauseContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_values_list_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
			value();
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(336);
				match(COMMA);
				setState(337);
				value();
				}
				}
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Column_aliasContext column_alias() {
			return getRuleContext(Column_aliasContext.class,0);
		}
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitColumn(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_column);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			expr();
			setState(345);
			_la = _input.LA(1);
			if (_la==AS || _la==ID) {
				{
				setState(344);
				column_alias();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(MySQLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(MySQLParser.ID, i);
		}
		public List<TerminalNode> DOT() { return getTokens(MySQLParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(MySQLParser.DOT, i);
		}
		public Schema_nameContext schema_name() {
			return getRuleContext(Schema_nameContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public TerminalNode USER_VAR() { return getToken(MySQLParser.USER_VAR, 0); }
		public All_coumn_nameContext all_coumn_name() {
			return getRuleContext(All_coumn_nameContext.class,0);
		}
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitColumn_name(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_column_name);
		try {
			setState(365);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(354);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(350);
					switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
					case 1:
						{
						setState(347);
						schema_name();
						setState(348);
						match(DOT);
						}
						break;
					}
					setState(352);
					match(ID);
					setState(353);
					match(DOT);
					}
					break;
				}
				setState(356);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(360);
				switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(357);
					table_alias();
					setState(358);
					match(DOT);
					}
					break;
				}
				setState(362);
				match(ID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(363);
				match(USER_VAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(364);
				all_coumn_name();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class All_coumn_nameContext extends ParserRuleContext {
		public TerminalNode ASTERISK() { return getToken(MySQLParser.ASTERISK, 0); }
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MySQLParser.DOT, 0); }
		public All_coumn_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all_coumn_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterAll_coumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitAll_coumn_name(this);
		}
	}

	public final All_coumn_nameContext all_coumn_name() throws RecognitionException {
		All_coumn_nameContext _localctx = new All_coumn_nameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_all_coumn_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			_la = _input.LA(1);
			if (_la==AS || _la==ID) {
				{
				setState(367);
				table_alias();
				setState(368);
				match(DOT);
				}
			}

			setState(372);
			match(ASTERISK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_aliasContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MySQLParser.ID, 0); }
		public TerminalNode AS() { return getToken(MySQLParser.AS, 0); }
		public Column_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterColumn_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitColumn_alias(this);
		}
	}

	public final Column_aliasContext column_alias() throws RecognitionException {
		Column_aliasContext _localctx = new Column_aliasContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_column_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(374);
				match(AS);
				}
			}

			setState(377);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MySQLParser.ID, 0); }
		public Index_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterIndex_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitIndex_name(this);
		}
	}

	public final Index_nameContext index_name() throws RecognitionException {
		Index_nameContext _localctx = new Index_nameContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_listContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(MySQLParser.LPAREN, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(MySQLParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public Column_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterColumn_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitColumn_list(this);
		}
	}

	public final Column_listContext column_list() throws RecognitionException {
		Column_listContext _localctx = new Column_listContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_column_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			match(LPAREN);
			setState(382);
			column_name();
			setState(387);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(383);
				match(COMMA);
				setState(384);
				column_name();
				}
				}
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(390);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_list_clauseContext extends ParserRuleContext {
		public List<ColumnContext> column() {
			return getRuleContexts(ColumnContext.class);
		}
		public ColumnContext column(int i) {
			return getRuleContext(ColumnContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public Column_list_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_list_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterColumn_list_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitColumn_list_clause(this);
		}
	}

	public final Column_list_clauseContext column_list_clause() throws RecognitionException {
		Column_list_clauseContext _localctx = new Column_list_clauseContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_column_list_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			column();
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(393);
				match(COMMA);
				setState(394);
				column();
				}
				}
				setState(399);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class From_clauseContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(MySQLParser.FROM, 0); }
		public List<Table_nameContext> table_name() {
			return getRuleContexts(Table_nameContext.class);
		}
		public Table_nameContext table_name(int i) {
			return getRuleContext(Table_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public From_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterFrom_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitFrom_clause(this);
		}
	}

	public final From_clauseContext from_clause() throws RecognitionException {
		From_clauseContext _localctx = new From_clauseContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_from_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(FROM);
			setState(401);
			table_name();
			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(402);
				match(COMMA);
				setState(403);
				table_name();
				}
				}
				setState(408);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Where_clauseContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(MySQLParser.WHERE, 0); }
		public Expression_clauseContext expression_clause() {
			return getRuleContext(Expression_clauseContext.class,0);
		}
		public Where_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterWhere_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitWhere_clause(this);
		}
	}

	public final Where_clauseContext where_clause() throws RecognitionException {
		Where_clauseContext _localctx = new Where_clauseContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_where_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			match(WHERE);
			setState(410);
			expression_clause();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_clauseContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<Expr_opContext> expr_op() {
			return getRuleContexts(Expr_opContext.class);
		}
		public Expr_opContext expr_op(int i) {
			return getRuleContext(Expr_opContext.class,i);
		}
		public Expression_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterExpression_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitExpression_clause(this);
		}
	}

	public final Expression_clauseContext expression_clause() throws RecognitionException {
		Expression_clauseContext _localctx = new Expression_clauseContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_expression_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			expression();
			setState(418);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR) | (1L << NOT))) != 0)) {
				{
				{
				setState(413);
				expr_op();
				setState(414);
				expression();
				}
				}
				setState(420);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Quoted_expressionContext quoted_expression() {
			return getRuleContext(Quoted_expressionContext.class,0);
		}
		public List<Simple_expressionContext> simple_expression() {
			return getRuleContexts(Simple_expressionContext.class);
		}
		public Simple_expressionContext simple_expression(int i) {
			return getRuleContext(Simple_expressionContext.class,i);
		}
		public List<Expr_opContext> expr_op() {
			return getRuleContexts(Expr_opContext.class);
		}
		public Expr_opContext expr_op(int i) {
			return getRuleContext(Expr_opContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_expression);
		try {
			int _alt;
			setState(433);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(421);
				quoted_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(422);
				simple_expression();
				setState(430);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(423);
						expr_op();
						setState(426);
						switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
						case 1:
							{
							setState(424);
							expression();
							}
							break;
						case 2:
							{
							setState(425);
							simple_expression();
							}
							break;
						}
						}
						} 
					}
					setState(432);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Quoted_expressionContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(MySQLParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MySQLParser.RPAREN, 0); }
		public Quoted_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quoted_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterQuoted_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitQuoted_expression(this);
		}
	}

	public final Quoted_expressionContext quoted_expression() throws RecognitionException {
		Quoted_expressionContext _localctx = new Quoted_expressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_quoted_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(LPAREN);
			setState(436);
			expression();
			setState(437);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Right_elementContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Right_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterRight_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitRight_element(this);
		}
	}

	public final Right_elementContext right_element() throws RecognitionException {
		Right_elementContext _localctx = new Right_elementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_right_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Left_elementContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Left_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterLeft_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitLeft_element(this);
		}
	}

	public final Left_elementContext left_element() throws RecognitionException {
		Left_elementContext _localctx = new Left_elementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_left_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Target_elementContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Target_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_target_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTarget_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTarget_element(this);
		}
	}

	public final Target_elementContext target_element() throws RecognitionException {
		Target_elementContext _localctx = new Target_elementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_target_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Relational_opContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(MySQLParser.EQ, 0); }
		public TerminalNode LTH() { return getToken(MySQLParser.LTH, 0); }
		public TerminalNode GTH() { return getToken(MySQLParser.GTH, 0); }
		public TerminalNode NOT_EQ() { return getToken(MySQLParser.NOT_EQ, 0); }
		public TerminalNode LET() { return getToken(MySQLParser.LET, 0); }
		public TerminalNode GET() { return getToken(MySQLParser.GET, 0); }
		public TerminalNode LIKE() { return getToken(MySQLParser.LIKE, 0); }
		public Relational_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterRelational_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitRelational_op(this);
		}
	}

	public final Relational_opContext relational_op() throws RecognitionException {
		Relational_opContext _localctx = new Relational_opContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_relational_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LIKE) | (1L << EQ) | (1L << LTH) | (1L << GTH) | (1L << NOT_EQ) | (1L << LET) | (1L << GET))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_opContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(MySQLParser.AND, 0); }
		public TerminalNode XOR() { return getToken(MySQLParser.XOR, 0); }
		public TerminalNode OR() { return getToken(MySQLParser.OR, 0); }
		public TerminalNode NOT() { return getToken(MySQLParser.NOT, 0); }
		public Expr_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterExpr_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitExpr_op(this);
		}
	}

	public final Expr_opContext expr_op() throws RecognitionException {
		Expr_opContext _localctx = new Expr_opContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_expr_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR) | (1L << NOT))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Between_opContext extends ParserRuleContext {
		public TerminalNode BETWEEN() { return getToken(MySQLParser.BETWEEN, 0); }
		public Between_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterBetween_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitBetween_op(this);
		}
	}

	public final Between_opContext between_op() throws RecognitionException {
		Between_opContext _localctx = new Between_opContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_between_op);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			match(BETWEEN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Is_or_is_notContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(MySQLParser.IS, 0); }
		public TerminalNode NOT() { return getToken(MySQLParser.NOT, 0); }
		public Is_or_is_notContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_is_or_is_not; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterIs_or_is_not(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitIs_or_is_not(this);
		}
	}

	public final Is_or_is_notContext is_or_is_not() throws RecognitionException {
		Is_or_is_notContext _localctx = new Is_or_is_notContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_is_or_is_not);
		try {
			setState(454);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(451);
				match(IS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(452);
				match(IS);
				setState(453);
				match(NOT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Simple_expressionContext extends ParserRuleContext {
		public Left_elementContext left_element() {
			return getRuleContext(Left_elementContext.class,0);
		}
		public Relational_opContext relational_op() {
			return getRuleContext(Relational_opContext.class,0);
		}
		public Right_elementContext right_element() {
			return getRuleContext(Right_elementContext.class,0);
		}
		public Target_elementContext target_element() {
			return getRuleContext(Target_elementContext.class,0);
		}
		public Between_opContext between_op() {
			return getRuleContext(Between_opContext.class,0);
		}
		public TerminalNode AND() { return getToken(MySQLParser.AND, 0); }
		public Is_or_is_notContext is_or_is_not() {
			return getRuleContext(Is_or_is_notContext.class,0);
		}
		public TerminalNode NULL() { return getToken(MySQLParser.NULL, 0); }
		public Simple_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterSimple_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitSimple_expression(this);
		}
	}

	public final Simple_expressionContext simple_expression() throws RecognitionException {
		Simple_expressionContext _localctx = new Simple_expressionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_simple_expression);
		try {
			setState(470);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(456);
				left_element();
				setState(457);
				relational_op();
				setState(458);
				right_element();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(460);
				target_element();
				setState(461);
				between_op();
				setState(462);
				left_element();
				setState(463);
				match(AND);
				setState(464);
				right_element();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(466);
				target_element();
				setState(467);
				is_or_is_not();
				setState(468);
				match(NULL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_referencesContext extends ParserRuleContext {
		public List<Table_referenceContext> table_reference() {
			return getRuleContexts(Table_referenceContext.class);
		}
		public Table_referenceContext table_reference(int i) {
			return getRuleContext(Table_referenceContext.class,i);
		}
		public List<Join_clauseContext> join_clause() {
			return getRuleContexts(Join_clauseContext.class);
		}
		public Join_clauseContext join_clause(int i) {
			return getRuleContext(Join_clauseContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public Table_referencesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_references; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTable_references(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTable_references(this);
		}
	}

	public final Table_referencesContext table_references() throws RecognitionException {
		Table_referencesContext _localctx = new Table_referencesContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_table_references);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			table_reference();
			setState(478);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 51)) & ~0x3f) == 0 && ((1L << (_la - 51)) & ((1L << (COMMA - 51)) | (1L << (INNER - 51)) | (1L << (JOIN - 51)) | (1L << (CROSS - 51)) | (1L << (STRAIGHT_JOIN - 51)) | (1L << (NATURAL - 51)) | (1L << (LEFT - 51)) | (1L << (RIGHT - 51)))) != 0)) {
				{
				setState(476);
				switch (_input.LA(1)) {
				case COMMA:
					{
					{
					setState(473);
					match(COMMA);
					setState(474);
					table_reference();
					}
					}
					break;
				case INNER:
				case JOIN:
				case CROSS:
				case STRAIGHT_JOIN:
				case NATURAL:
				case LEFT:
				case RIGHT:
					{
					setState(475);
					join_clause();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(480);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_referenceContext extends ParserRuleContext {
		public Table_atomContext table_atom() {
			return getRuleContext(Table_atomContext.class,0);
		}
		public Table_referenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTable_reference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTable_reference(this);
		}
	}

	public final Table_referenceContext table_reference() throws RecognitionException {
		Table_referenceContext _localctx = new Table_referenceContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_table_reference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
			table_atom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_factor1Context extends ParserRuleContext {
		public Table_factor2Context table_factor2() {
			return getRuleContext(Table_factor2Context.class,0);
		}
		public TerminalNode JOIN() { return getToken(MySQLParser.JOIN, 0); }
		public Table_atomContext table_atom() {
			return getRuleContext(Table_atomContext.class,0);
		}
		public Join_conditionContext join_condition() {
			return getRuleContext(Join_conditionContext.class,0);
		}
		public TerminalNode INNER() { return getToken(MySQLParser.INNER, 0); }
		public TerminalNode CROSS() { return getToken(MySQLParser.CROSS, 0); }
		public Table_factor1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_factor1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTable_factor1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTable_factor1(this);
		}
	}

	public final Table_factor1Context table_factor1() throws RecognitionException {
		Table_factor1Context _localctx = new Table_factor1Context(_ctx, getState());
		enterRule(_localctx, 80, RULE_table_factor1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			table_factor2();
			setState(492);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INNER) | (1L << JOIN) | (1L << CROSS))) != 0)) {
				{
				setState(485);
				_la = _input.LA(1);
				if (_la==INNER || _la==CROSS) {
					{
					setState(484);
					_la = _input.LA(1);
					if ( !(_la==INNER || _la==CROSS) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				setState(487);
				match(JOIN);
				setState(488);
				table_atom();
				setState(490);
				_la = _input.LA(1);
				if (_la==USING || _la==ON) {
					{
					setState(489);
					join_condition();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_factor2Context extends ParserRuleContext {
		public Table_factor3Context table_factor3() {
			return getRuleContext(Table_factor3Context.class,0);
		}
		public TerminalNode STRAIGHT_JOIN() { return getToken(MySQLParser.STRAIGHT_JOIN, 0); }
		public Table_atomContext table_atom() {
			return getRuleContext(Table_atomContext.class,0);
		}
		public TerminalNode ON() { return getToken(MySQLParser.ON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Table_factor2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_factor2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTable_factor2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTable_factor2(this);
		}
	}

	public final Table_factor2Context table_factor2() throws RecognitionException {
		Table_factor2Context _localctx = new Table_factor2Context(_ctx, getState());
		enterRule(_localctx, 82, RULE_table_factor2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			table_factor3();
			setState(501);
			_la = _input.LA(1);
			if (_la==STRAIGHT_JOIN) {
				{
				setState(495);
				match(STRAIGHT_JOIN);
				setState(496);
				table_atom();
				setState(499);
				_la = _input.LA(1);
				if (_la==ON) {
					{
					setState(497);
					match(ON);
					setState(498);
					expression();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_factor3Context extends ParserRuleContext {
		public List<Table_factor4Context> table_factor4() {
			return getRuleContexts(Table_factor4Context.class);
		}
		public Table_factor4Context table_factor4(int i) {
			return getRuleContext(Table_factor4Context.class,i);
		}
		public TerminalNode JOIN() { return getToken(MySQLParser.JOIN, 0); }
		public Join_conditionContext join_condition() {
			return getRuleContext(Join_conditionContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(MySQLParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(MySQLParser.RIGHT, 0); }
		public TerminalNode OUTER() { return getToken(MySQLParser.OUTER, 0); }
		public Table_factor3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_factor3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTable_factor3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTable_factor3(this);
		}
	}

	public final Table_factor3Context table_factor3() throws RecognitionException {
		Table_factor3Context _localctx = new Table_factor3Context(_ctx, getState());
		enterRule(_localctx, 84, RULE_table_factor3);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
			table_factor4();
			setState(512);
			_la = _input.LA(1);
			if (_la==LEFT || _la==RIGHT) {
				{
				setState(504);
				_la = _input.LA(1);
				if ( !(_la==LEFT || _la==RIGHT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(506);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(505);
					match(OUTER);
					}
				}

				setState(508);
				match(JOIN);
				setState(509);
				table_factor4();
				setState(510);
				join_condition();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_factor4Context extends ParserRuleContext {
		public List<Table_atomContext> table_atom() {
			return getRuleContexts(Table_atomContext.class);
		}
		public Table_atomContext table_atom(int i) {
			return getRuleContext(Table_atomContext.class,i);
		}
		public TerminalNode NATURAL() { return getToken(MySQLParser.NATURAL, 0); }
		public TerminalNode JOIN() { return getToken(MySQLParser.JOIN, 0); }
		public TerminalNode LEFT() { return getToken(MySQLParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(MySQLParser.RIGHT, 0); }
		public TerminalNode OUTER() { return getToken(MySQLParser.OUTER, 0); }
		public Table_factor4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_factor4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTable_factor4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTable_factor4(this);
		}
	}

	public final Table_factor4Context table_factor4() throws RecognitionException {
		Table_factor4Context _localctx = new Table_factor4Context(_ctx, getState());
		enterRule(_localctx, 86, RULE_table_factor4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
			table_atom();
			setState(524);
			_la = _input.LA(1);
			if (_la==NATURAL) {
				{
				setState(515);
				match(NATURAL);
				setState(520);
				_la = _input.LA(1);
				if (_la==LEFT || _la==RIGHT) {
					{
					setState(516);
					_la = _input.LA(1);
					if ( !(_la==LEFT || _la==RIGHT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(518);
					_la = _input.LA(1);
					if (_la==OUTER) {
						{
						setState(517);
						match(OUTER);
						}
					}

					}
				}

				setState(522);
				match(JOIN);
				setState(523);
				table_atom();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_atomContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Partition_clauseContext partition_clause() {
			return getRuleContext(Partition_clauseContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public Index_hint_listContext index_hint_list() {
			return getRuleContext(Index_hint_listContext.class,0);
		}
		public SubqueryContext subquery() {
			return getRuleContext(SubqueryContext.class,0);
		}
		public Subquery_aliasContext subquery_alias() {
			return getRuleContext(Subquery_aliasContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(MySQLParser.LPAREN, 0); }
		public Table_referencesContext table_references() {
			return getRuleContext(Table_referencesContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MySQLParser.RPAREN, 0); }
		public TerminalNode OJ() { return getToken(MySQLParser.OJ, 0); }
		public List<Table_referenceContext> table_reference() {
			return getRuleContexts(Table_referenceContext.class);
		}
		public Table_referenceContext table_reference(int i) {
			return getRuleContext(Table_referenceContext.class,i);
		}
		public TerminalNode LEFT() { return getToken(MySQLParser.LEFT, 0); }
		public TerminalNode OUTER() { return getToken(MySQLParser.OUTER, 0); }
		public TerminalNode JOIN() { return getToken(MySQLParser.JOIN, 0); }
		public TerminalNode ON() { return getToken(MySQLParser.ON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Table_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterTable_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitTable_atom(this);
		}
	}

	public final Table_atomContext table_atom() throws RecognitionException {
		Table_atomContext _localctx = new Table_atomContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_table_atom);
		int _la;
		try {
			setState(552);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(526);
				table_name();
				setState(528);
				_la = _input.LA(1);
				if (_la==PARTITION) {
					{
					setState(527);
					partition_clause();
					}
				}

				setState(531);
				_la = _input.LA(1);
				if (_la==AS || _la==ID) {
					{
					setState(530);
					table_alias();
					}
				}

				setState(534);
				_la = _input.LA(1);
				if (_la==USE || _la==IGNORE) {
					{
					setState(533);
					index_hint_list();
					}
				}

				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(536);
				subquery();
				setState(537);
				subquery_alias();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(539);
				match(LPAREN);
				setState(540);
				table_references();
				setState(541);
				match(RPAREN);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(543);
				match(OJ);
				setState(544);
				table_reference();
				setState(545);
				match(LEFT);
				setState(546);
				match(OUTER);
				setState(547);
				match(JOIN);
				setState(548);
				table_reference();
				setState(549);
				match(ON);
				setState(550);
				expression();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_clauseContext extends ParserRuleContext {
		public TerminalNode JOIN() { return getToken(MySQLParser.JOIN, 0); }
		public Table_atomContext table_atom() {
			return getRuleContext(Table_atomContext.class,0);
		}
		public Join_conditionContext join_condition() {
			return getRuleContext(Join_conditionContext.class,0);
		}
		public TerminalNode INNER() { return getToken(MySQLParser.INNER, 0); }
		public TerminalNode CROSS() { return getToken(MySQLParser.CROSS, 0); }
		public TerminalNode STRAIGHT_JOIN() { return getToken(MySQLParser.STRAIGHT_JOIN, 0); }
		public TerminalNode ON() { return getToken(MySQLParser.ON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Table_factor4Context table_factor4() {
			return getRuleContext(Table_factor4Context.class,0);
		}
		public TerminalNode LEFT() { return getToken(MySQLParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(MySQLParser.RIGHT, 0); }
		public TerminalNode OUTER() { return getToken(MySQLParser.OUTER, 0); }
		public TerminalNode NATURAL() { return getToken(MySQLParser.NATURAL, 0); }
		public Join_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterJoin_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitJoin_clause(this);
		}
	}

	public final Join_clauseContext join_clause() throws RecognitionException {
		Join_clauseContext _localctx = new Join_clauseContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_join_clause);
		int _la;
		try {
			setState(585);
			switch (_input.LA(1)) {
			case INNER:
			case JOIN:
			case CROSS:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(555);
				_la = _input.LA(1);
				if (_la==INNER || _la==CROSS) {
					{
					setState(554);
					_la = _input.LA(1);
					if ( !(_la==INNER || _la==CROSS) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				setState(557);
				match(JOIN);
				setState(558);
				table_atom();
				setState(560);
				_la = _input.LA(1);
				if (_la==USING || _la==ON) {
					{
					setState(559);
					join_condition();
					}
				}

				}
				}
				break;
			case STRAIGHT_JOIN:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(562);
				match(STRAIGHT_JOIN);
				setState(563);
				table_atom();
				setState(566);
				_la = _input.LA(1);
				if (_la==ON) {
					{
					setState(564);
					match(ON);
					setState(565);
					expression();
					}
				}

				}
				}
				break;
			case LEFT:
			case RIGHT:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(568);
				_la = _input.LA(1);
				if ( !(_la==LEFT || _la==RIGHT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(570);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(569);
					match(OUTER);
					}
				}

				setState(572);
				match(JOIN);
				setState(573);
				table_factor4();
				setState(574);
				join_condition();
				}
				}
				break;
			case NATURAL:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(576);
				match(NATURAL);
				setState(581);
				_la = _input.LA(1);
				if (_la==LEFT || _la==RIGHT) {
					{
					setState(577);
					_la = _input.LA(1);
					if ( !(_la==LEFT || _la==RIGHT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(579);
					_la = _input.LA(1);
					if (_la==OUTER) {
						{
						setState(578);
						match(OUTER);
						}
					}

					}
				}

				setState(583);
				match(JOIN);
				setState(584);
				table_atom();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_conditionContext extends ParserRuleContext {
		public TerminalNode ON() { return getToken(MySQLParser.ON, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<Expr_opContext> expr_op() {
			return getRuleContexts(Expr_opContext.class);
		}
		public Expr_opContext expr_op(int i) {
			return getRuleContext(Expr_opContext.class,i);
		}
		public TerminalNode USING() { return getToken(MySQLParser.USING, 0); }
		public Column_listContext column_list() {
			return getRuleContext(Column_listContext.class,0);
		}
		public Join_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterJoin_condition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitJoin_condition(this);
		}
	}

	public final Join_conditionContext join_condition() throws RecognitionException {
		Join_conditionContext _localctx = new Join_conditionContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_join_condition);
		int _la;
		try {
			setState(599);
			switch (_input.LA(1)) {
			case ON:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(587);
				match(ON);
				setState(588);
				expression();
				setState(594);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR) | (1L << NOT))) != 0)) {
					{
					{
					setState(589);
					expr_op();
					setState(590);
					expression();
					}
					}
					setState(596);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case USING:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(597);
				match(USING);
				setState(598);
				column_list();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_hint_listContext extends ParserRuleContext {
		public List<Index_hintContext> index_hint() {
			return getRuleContexts(Index_hintContext.class);
		}
		public Index_hintContext index_hint(int i) {
			return getRuleContext(Index_hintContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public Index_hint_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_hint_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterIndex_hint_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitIndex_hint_list(this);
		}
	}

	public final Index_hint_listContext index_hint_list() throws RecognitionException {
		Index_hint_listContext _localctx = new Index_hint_listContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_index_hint_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(601);
			index_hint();
			setState(606);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(602);
					match(COMMA);
					setState(603);
					index_hint();
					}
					} 
				}
				setState(608);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_optionsContext extends ParserRuleContext {
		public TerminalNode INDEX() { return getToken(MySQLParser.INDEX, 0); }
		public TerminalNode KEY() { return getToken(MySQLParser.KEY, 0); }
		public TerminalNode FOR() { return getToken(MySQLParser.FOR, 0); }
		public TerminalNode JOIN() { return getToken(MySQLParser.JOIN, 0); }
		public TerminalNode ORDER() { return getToken(MySQLParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(MySQLParser.BY, 0); }
		public TerminalNode GROUP() { return getToken(MySQLParser.GROUP, 0); }
		public Index_optionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_options; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterIndex_options(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitIndex_options(this);
		}
	}

	public final Index_optionsContext index_options() throws RecognitionException {
		Index_optionsContext _localctx = new Index_optionsContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_index_options);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(609);
			_la = _input.LA(1);
			if ( !(_la==INDEX || _la==KEY) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(618);
			_la = _input.LA(1);
			if (_la==FOR) {
				{
				setState(610);
				match(FOR);
				setState(616);
				switch (_input.LA(1)) {
				case JOIN:
					{
					{
					setState(611);
					match(JOIN);
					}
					}
					break;
				case ORDER:
					{
					{
					setState(612);
					match(ORDER);
					setState(613);
					match(BY);
					}
					}
					break;
				case GROUP:
					{
					{
					setState(614);
					match(GROUP);
					setState(615);
					match(BY);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_hintContext extends ParserRuleContext {
		public TerminalNode USE() { return getToken(MySQLParser.USE, 0); }
		public Index_optionsContext index_options() {
			return getRuleContext(Index_optionsContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(MySQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MySQLParser.RPAREN, 0); }
		public Index_listContext index_list() {
			return getRuleContext(Index_listContext.class,0);
		}
		public TerminalNode IGNORE() { return getToken(MySQLParser.IGNORE, 0); }
		public Index_hintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_hint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterIndex_hint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitIndex_hint(this);
		}
	}

	public final Index_hintContext index_hint() throws RecognitionException {
		Index_hintContext _localctx = new Index_hintContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_index_hint);
		int _la;
		try {
			setState(634);
			switch (_input.LA(1)) {
			case USE:
				enterOuterAlt(_localctx, 1);
				{
				setState(620);
				match(USE);
				setState(621);
				index_options();
				setState(622);
				match(LPAREN);
				setState(624);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(623);
					index_list();
					}
				}

				setState(626);
				match(RPAREN);
				}
				break;
			case IGNORE:
				enterOuterAlt(_localctx, 2);
				{
				setState(628);
				match(IGNORE);
				setState(629);
				index_options();
				setState(630);
				match(LPAREN);
				setState(631);
				index_list();
				setState(632);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_listContext extends ParserRuleContext {
		public List<Index_nameContext> index_name() {
			return getRuleContexts(Index_nameContext.class);
		}
		public Index_nameContext index_name(int i) {
			return getRuleContext(Index_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public Index_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterIndex_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitIndex_list(this);
		}
	}

	public final Index_listContext index_list() throws RecognitionException {
		Index_listContext _localctx = new Index_listContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_index_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(636);
			index_name();
			setState(641);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(637);
				match(COMMA);
				setState(638);
				index_name();
				}
				}
				setState(643);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Partition_clauseContext extends ParserRuleContext {
		public TerminalNode PARTITION() { return getToken(MySQLParser.PARTITION, 0); }
		public TerminalNode LPAREN() { return getToken(MySQLParser.LPAREN, 0); }
		public Partition_namesContext partition_names() {
			return getRuleContext(Partition_namesContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MySQLParser.RPAREN, 0); }
		public Partition_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partition_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterPartition_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitPartition_clause(this);
		}
	}

	public final Partition_clauseContext partition_clause() throws RecognitionException {
		Partition_clauseContext _localctx = new Partition_clauseContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_partition_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(644);
			match(PARTITION);
			setState(645);
			match(LPAREN);
			setState(646);
			partition_names();
			setState(647);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Partition_namesContext extends ParserRuleContext {
		public List<Partition_nameContext> partition_name() {
			return getRuleContexts(Partition_nameContext.class);
		}
		public Partition_nameContext partition_name(int i) {
			return getRuleContext(Partition_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySQLParser.COMMA, i);
		}
		public Partition_namesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partition_names; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterPartition_names(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitPartition_names(this);
		}
	}

	public final Partition_namesContext partition_names() throws RecognitionException {
		Partition_namesContext _localctx = new Partition_namesContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_partition_names);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(649);
			partition_name();
			setState(654);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(650);
				match(COMMA);
				setState(651);
				partition_name();
				}
				}
				setState(656);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Partition_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MySQLParser.ID, 0); }
		public Partition_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partition_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterPartition_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitPartition_name(this);
		}
	}

	public final Partition_nameContext partition_name() throws RecognitionException {
		Partition_nameContext _localctx = new Partition_nameContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_partition_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(657);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Subquery_aliasContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MySQLParser.ID, 0); }
		public TerminalNode AS() { return getToken(MySQLParser.AS, 0); }
		public Subquery_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subquery_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterSubquery_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitSubquery_alias(this);
		}
	}

	public final Subquery_aliasContext subquery_alias() throws RecognitionException {
		Subquery_aliasContext _localctx = new Subquery_aliasContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_subquery_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(660);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(659);
				match(AS);
				}
			}

			setState(662);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubqueryContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(MySQLParser.LPAREN, 0); }
		public Select_clauseContext select_clause() {
			return getRuleContext(Select_clauseContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MySQLParser.RPAREN, 0); }
		public SubqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subquery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterSubquery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitSubquery(this);
		}
	}

	public final SubqueryContext subquery() throws RecognitionException {
		SubqueryContext _localctx = new SubqueryContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_subquery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(664);
			match(LPAREN);
			setState(665);
			select_clause();
			setState(666);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OffsetContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MySQLParser.INT, 0); }
		public OffsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_offset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterOffset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitOffset(this);
		}
	}

	public final OffsetContext offset() throws RecognitionException {
		OffsetContext _localctx = new OffsetContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_offset);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Row_countContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MySQLParser.INT, 0); }
		public Row_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_row_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).enterRow_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySQLListener ) ((MySQLListener)listener).exitRow_count(this);
		}
	}

	public final Row_countContext row_count() throws RecognitionException {
		Row_countContext _localctx = new Row_countContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_row_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(670);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return term_sempred((TermContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3X\u02a3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\5\3\u0080\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\7\3\u008e\n\3\f\3\16\3\u0091\13\3\3\4\3\4\3\4\3\4\3\4"+
		"\5\4\u0098\n\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6\u00a1\n\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\b\7\b\u00ad\n\b\f\b\16\b\u00b0\13\b\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\5\n\u00ba\n\n\3\13\3\13\3\f\3\f\3\f\3\f\5\f\u00c2"+
		"\n\f\3\f\5\f\u00c5\n\f\3\f\3\f\3\f\3\f\5\f\u00cb\n\f\3\f\3\f\5\f\u00cf"+
		"\n\f\5\f\u00d1\n\f\3\f\3\f\3\f\3\f\5\f\u00d7\n\f\5\f\u00d9\n\f\3\f\3\f"+
		"\5\f\u00dd\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e4\n\f\3\r\3\r\3\r\3\r\3\r\5"+
		"\r\u00eb\n\r\3\r\3\r\3\r\3\r\5\r\u00f1\n\r\5\r\u00f3\n\r\3\r\3\r\5\r\u00f7"+
		"\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u00fe\n\r\3\16\3\16\5\16\u0102\n\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u0109\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\6\16\u0115\n\16\r\16\16\16\u0116\5\16\u0119\n\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0122\n\17\3\17\3\17\3\17\3\17\3\17"+
		"\5\17\u0129\n\17\7\17\u012b\n\17\f\17\16\17\u012e\13\17\3\17\3\17\5\17"+
		"\u0132\n\17\3\17\3\17\3\17\3\17\5\17\u0138\n\17\5\17\u013a\n\17\3\17\3"+
		"\17\5\17\u013e\n\17\3\17\3\17\3\17\3\17\3\17\5\17\u0145\n\17\3\20\3\20"+
		"\5\20\u0149\n\20\3\21\5\21\u014c\n\21\3\21\3\21\3\22\3\22\3\23\3\23\3"+
		"\23\7\23\u0155\n\23\f\23\16\23\u0158\13\23\3\24\3\24\5\24\u015c\n\24\3"+
		"\25\3\25\3\25\5\25\u0161\n\25\3\25\3\25\5\25\u0165\n\25\3\25\3\25\3\25"+
		"\3\25\5\25\u016b\n\25\3\25\3\25\3\25\5\25\u0170\n\25\3\26\3\26\3\26\5"+
		"\26\u0175\n\26\3\26\3\26\3\27\5\27\u017a\n\27\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\7\31\u0184\n\31\f\31\16\31\u0187\13\31\3\31\3\31\3\32"+
		"\3\32\3\32\7\32\u018e\n\32\f\32\16\32\u0191\13\32\3\33\3\33\3\33\3\33"+
		"\7\33\u0197\n\33\f\33\16\33\u019a\13\33\3\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\7\35\u01a3\n\35\f\35\16\35\u01a6\13\35\3\36\3\36\3\36\3\36\3\36"+
		"\5\36\u01ad\n\36\7\36\u01af\n\36\f\36\16\36\u01b2\13\36\5\36\u01b4\n\36"+
		"\3\37\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\5"+
		"&\u01c9\n&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'"+
		"\u01d9\n\'\3(\3(\3(\3(\7(\u01df\n(\f(\16(\u01e2\13(\3)\3)\3*\3*\5*\u01e8"+
		"\n*\3*\3*\3*\5*\u01ed\n*\5*\u01ef\n*\3+\3+\3+\3+\3+\5+\u01f6\n+\5+\u01f8"+
		"\n+\3,\3,\3,\5,\u01fd\n,\3,\3,\3,\3,\5,\u0203\n,\3-\3-\3-\3-\5-\u0209"+
		"\n-\5-\u020b\n-\3-\3-\5-\u020f\n-\3.\3.\5.\u0213\n.\3.\5.\u0216\n.\3."+
		"\5.\u0219\n.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\5.\u022b"+
		"\n.\3/\5/\u022e\n/\3/\3/\3/\5/\u0233\n/\3/\3/\3/\3/\5/\u0239\n/\3/\3/"+
		"\5/\u023d\n/\3/\3/\3/\3/\3/\3/\3/\5/\u0246\n/\5/\u0248\n/\3/\3/\5/\u024c"+
		"\n/\3\60\3\60\3\60\3\60\3\60\7\60\u0253\n\60\f\60\16\60\u0256\13\60\3"+
		"\60\3\60\5\60\u025a\n\60\3\61\3\61\3\61\7\61\u025f\n\61\f\61\16\61\u0262"+
		"\13\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\5\62\u026b\n\62\5\62\u026d\n"+
		"\62\3\63\3\63\3\63\3\63\5\63\u0273\n\63\3\63\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\5\63\u027d\n\63\3\64\3\64\3\64\7\64\u0282\n\64\f\64\16\64\u0285"+
		"\13\64\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\7\66\u028f\n\66\f\66\16"+
		"\66\u0292\13\66\3\67\3\67\38\58\u0297\n8\38\38\39\39\39\39\3:\3:\3;\3"+
		";\3;\2\3\4<\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprt\2\t\3\2\35\36\3\2MN\5\2\22\22-\60\62"+
		"\63\4\2\r\17\61\61\4\288;;\3\2HI\3\2=>\u02d0\2v\3\2\2\2\4\177\3\2\2\2"+
		"\6\u0097\3\2\2\2\b\u0099\3\2\2\2\n\u009d\3\2\2\2\f\u00a2\3\2\2\2\16\u00a7"+
		"\3\2\2\2\20\u00b3\3\2\2\2\22\u00b9\3\2\2\2\24\u00bb\3\2\2\2\26\u00bd\3"+
		"\2\2\2\30\u00e5\3\2\2\2\32\u0118\3\2\2\2\34\u011a\3\2\2\2\36\u0146\3\2"+
		"\2\2 \u014b\3\2\2\2\"\u014f\3\2\2\2$\u0151\3\2\2\2&\u0159\3\2\2\2(\u016f"+
		"\3\2\2\2*\u0174\3\2\2\2,\u0179\3\2\2\2.\u017d\3\2\2\2\60\u017f\3\2\2\2"+
		"\62\u018a\3\2\2\2\64\u0192\3\2\2\2\66\u019b\3\2\2\28\u019e\3\2\2\2:\u01b3"+
		"\3\2\2\2<\u01b5\3\2\2\2>\u01b9\3\2\2\2@\u01bb\3\2\2\2B\u01bd\3\2\2\2D"+
		"\u01bf\3\2\2\2F\u01c1\3\2\2\2H\u01c3\3\2\2\2J\u01c8\3\2\2\2L\u01d8\3\2"+
		"\2\2N\u01da\3\2\2\2P\u01e3\3\2\2\2R\u01e5\3\2\2\2T\u01f0\3\2\2\2V\u01f9"+
		"\3\2\2\2X\u0204\3\2\2\2Z\u022a\3\2\2\2\\\u024b\3\2\2\2^\u0259\3\2\2\2"+
		"`\u025b\3\2\2\2b\u0263\3\2\2\2d\u027c\3\2\2\2f\u027e\3\2\2\2h\u0286\3"+
		"\2\2\2j\u028b\3\2\2\2l\u0293\3\2\2\2n\u0296\3\2\2\2p\u029a\3\2\2\2r\u029e"+
		"\3\2\2\2t\u02a0\3\2\2\2vw\5\4\3\2w\3\3\2\2\2xy\b\3\1\2yz\7)\2\2z{\5\4"+
		"\3\2{|\7(\2\2|\u0080\3\2\2\2}\u0080\5\6\4\2~\u0080\5\16\b\2\177x\3\2\2"+
		"\2\177}\3\2\2\2\177~\3\2\2\2\u0080\u008f\3\2\2\2\u0081\u0082\f\6\2\2\u0082"+
		"\u0083\7\"\2\2\u0083\u008e\5\4\3\6\u0084\u0085\f\5\2\2\u0085\u0086\7\31"+
		"\2\2\u0086\u008e\5\4\3\6\u0087\u0088\f\4\2\2\u0088\u0089\7\'\2\2\u0089"+
		"\u008e\5\4\3\5\u008a\u008b\f\3\2\2\u008b\u008c\t\2\2\2\u008c\u008e\5\4"+
		"\3\4\u008d\u0081\3\2\2\2\u008d\u0084\3\2\2\2\u008d\u0087\3\2\2\2\u008d"+
		"\u008a\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2"+
		"\2\2\u0090\5\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0098\5\n\6\2\u0093\u0098"+
		"\5\f\7\2\u0094\u0098\5(\25\2\u0095\u0098\7X\2\2\u0096\u0098\5\b\5\2\u0097"+
		"\u0092\3\2\2\2\u0097\u0093\3\2\2\2\u0097\u0094\3\2\2\2\u0097\u0095\3\2"+
		"\2\2\u0097\u0096\3\2\2\2\u0098\7\3\2\2\2\u0099\u009a\7R\2\2\u009a\u009b"+
		"\7T\2\2\u009b\u009c\7R\2\2\u009c\t\3\2\2\2\u009d\u00a0\7U\2\2\u009e\u009f"+
		"\7\66\2\2\u009f\u00a1\7U\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\13\3\2\2\2\u00a2\u00a3\7)\2\2\u00a3\u00a4\7\36\2\2\u00a4\u00a5\5\n\6"+
		"\2\u00a5\u00a6\7(\2\2\u00a6\r\3\2\2\2\u00a7\u00a8\5\20\t\2\u00a8\u00a9"+
		"\7)\2\2\u00a9\u00ae\5\4\3\2\u00aa\u00ab\7\65\2\2\u00ab\u00ad\5\4\3\2\u00ac"+
		"\u00aa\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2"+
		"\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\7(\2\2\u00b2"+
		"\17\3\2\2\2\u00b3\u00b4\7T\2\2\u00b4\21\3\2\2\2\u00b5\u00ba\5\26\f\2\u00b6"+
		"\u00ba\5\30\r\2\u00b7\u00ba\5\32\16\2\u00b8\u00ba\5\34\17\2\u00b9\u00b5"+
		"\3\2\2\2\u00b9\u00b6\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00b8\3\2\2\2\u00ba"+
		"\23\3\2\2\2\u00bb\u00bc\7T\2\2\u00bc\25\3\2\2\2\u00bd\u00be\7\3\2\2\u00be"+
		"\u00c1\5\62\32\2\u00bf\u00c0\7\t\2\2\u00c0\u00c2\5N(\2\u00c1\u00bf\3\2"+
		"\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c5\5\66\34\2\u00c4"+
		"\u00c3\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00d0\3\2\2\2\u00c6\u00c7\7@"+
		"\2\2\u00c7\u00c8\7A\2\2\u00c8\u00ca\5\62\32\2\u00c9\u00cb\t\3\2\2\u00ca"+
		"\u00c9\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00cd\7O"+
		"\2\2\u00cd\u00cf\5:\36\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\u00d1\3\2\2\2\u00d0\u00c6\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d8\3\2"+
		"\2\2\u00d2\u00d3\7?\2\2\u00d3\u00d4\7A\2\2\u00d4\u00d6\5\62\32\2\u00d5"+
		"\u00d7\t\3\2\2\u00d6\u00d5\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\3\2"+
		"\2\2\u00d8\u00d2\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00e3\3\2\2\2\u00da"+
		"\u00dc\7L\2\2\u00db\u00dd\5r:\2\u00dc\u00db\3\2\2\2\u00dc\u00dd\3\2\2"+
		"\2\u00dd\u00de\3\2\2\2\u00de\u00e4\5t;\2\u00df\u00e0\5t;\2\u00e0\u00e1"+
		"\7P\2\2\u00e1\u00e2\5r:\2\u00e2\u00e4\3\2\2\2\u00e3\u00da\3\2\2\2\u00e3"+
		"\u00df\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\27\3\2\2\2\u00e5\u00e6\7\4\2"+
		"\2\u00e6\u00e7\7\t\2\2\u00e7\u00ea\5N(\2\u00e8\u00e9\7\13\2\2\u00e9\u00eb"+
		"\5:\36\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00f2\3\2\2\2\u00ec"+
		"\u00ed\7?\2\2\u00ed\u00ee\7A\2\2\u00ee\u00f0\5\62\32\2\u00ef\u00f1\t\3"+
		"\2\2\u00f0\u00ef\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2\2\2\u00f2"+
		"\u00ec\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00fd\3\2\2\2\u00f4\u00f6\7L"+
		"\2\2\u00f5\u00f7\5r:\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8"+
		"\3\2\2\2\u00f8\u00fe\5t;\2\u00f9\u00fa\5t;\2\u00fa\u00fb\7P\2\2\u00fb"+
		"\u00fc\5r:\2\u00fc\u00fe\3\2\2\2\u00fd\u00f4\3\2\2\2\u00fd\u00f9\3\2\2"+
		"\2\u00fd\u00fe\3\2\2\2\u00fe\31\3\2\2\2\u00ff\u0101\7\6\2\2\u0100\u0102"+
		"\7\7\2\2\u0101\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"\u0108\5N(\2\u0104\u0105\7)\2\2\u0105\u0106\5\62\32\2\u0106\u0107\7(\2"+
		"\2\u0107\u0109\3\2\2\2\u0108\u0104\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a"+
		"\3\2\2\2\u010a\u010b\7\b\2\2\u010b\u010c\7)\2\2\u010c\u010d\5$\23\2\u010d"+
		"\u010e\7(\2\2\u010e\u0119\3\2\2\2\u010f\u0114\7\n\2\2\u0110\u0111\5(\25"+
		"\2\u0111\u0112\7-\2\2\u0112\u0113\5\"\22\2\u0113\u0115\3\2\2\2\u0114\u0110"+
		"\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117"+
		"\u0119\3\2\2\2\u0118\u00ff\3\2\2\2\u0118\u010f\3\2\2\2\u0119\33\3\2\2"+
		"\2\u011a\u011b\7\5\2\2\u011b\u011c\5N(\2\u011c\u011d\7\n\2\2\u011d\u011e"+
		"\5(\25\2\u011e\u0121\7-\2\2\u011f\u0122\5\2\2\2\u0120\u0122\7Q\2\2\u0121"+
		"\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122\u012c\3\2\2\2\u0123\u0124\7\65"+
		"\2\2\u0124\u0125\5(\25\2\u0125\u0128\7-\2\2\u0126\u0129\5\2\2\2\u0127"+
		"\u0129\7Q\2\2\u0128\u0126\3\2\2\2\u0128\u0127\3\2\2\2\u0129\u012b\3\2"+
		"\2\2\u012a\u0123\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c"+
		"\u012d\3\2\2\2\u012d\u0131\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130\7\13"+
		"\2\2\u0130\u0132\5:\36\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132"+
		"\u0139\3\2\2\2\u0133\u0134\7?\2\2\u0134\u0135\7A\2\2\u0135\u0137\5\62"+
		"\32\2\u0136\u0138\t\3\2\2\u0137\u0136\3\2\2\2\u0137\u0138\3\2\2\2\u0138"+
		"\u013a\3\2\2\2\u0139\u0133\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u0144\3\2"+
		"\2\2\u013b\u013d\7L\2\2\u013c\u013e\5r:\2\u013d\u013c\3\2\2\2\u013d\u013e"+
		"\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0145\5t;\2\u0140\u0141\5t;\2\u0141"+
		"\u0142\7P\2\2\u0142\u0143\5r:\2\u0143\u0145\3\2\2\2\u0144\u013b\3\2\2"+
		"\2\u0144\u0140\3\2\2\2\u0144\u0145\3\2\2\2\u0145\35\3\2\2\2\u0146\u0148"+
		"\7T\2\2\u0147\u0149\5 \21\2\u0148\u0147\3\2\2\2\u0148\u0149\3\2\2\2\u0149"+
		"\37\3\2\2\2\u014a\u014c\7\f\2\2\u014b\u014a\3\2\2\2\u014b\u014c\3\2\2"+
		"\2\u014c\u014d\3\2\2\2\u014d\u014e\7T\2\2\u014e!\3\2\2\2\u014f\u0150\5"+
		"\2\2\2\u0150#\3\2\2\2\u0151\u0156\5\"\22\2\u0152\u0153\7\65\2\2\u0153"+
		"\u0155\5\"\22\2\u0154\u0152\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0154\3"+
		"\2\2\2\u0156\u0157\3\2\2\2\u0157%\3\2\2\2\u0158\u0156\3\2\2\2\u0159\u015b"+
		"\5\2\2\2\u015a\u015c\5,\27\2\u015b\u015a\3\2\2\2\u015b\u015c\3\2\2\2\u015c"+
		"\'\3\2\2\2\u015d\u015e\5\24\13\2\u015e\u015f\7\66\2\2\u015f\u0161\3\2"+
		"\2\2\u0160\u015d\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0162\3\2\2\2\u0162"+
		"\u0163\7T\2\2\u0163\u0165\7\66\2\2\u0164\u0160\3\2\2\2\u0164\u0165\3\2"+
		"\2\2\u0165\u0166\3\2\2\2\u0166\u0170\7T\2\2\u0167\u0168\5 \21\2\u0168"+
		"\u0169\7\66\2\2\u0169\u016b\3\2\2\2\u016a\u0167\3\2\2\2\u016a\u016b\3"+
		"\2\2\2\u016b\u016c\3\2\2\2\u016c\u0170\7T\2\2\u016d\u0170\7X\2\2\u016e"+
		"\u0170\5*\26\2\u016f\u0164\3\2\2\2\u016f\u016a\3\2\2\2\u016f\u016d\3\2"+
		"\2\2\u016f\u016e\3\2\2\2\u0170)\3\2\2\2\u0171\u0172\5 \21\2\u0172\u0173"+
		"\7\66\2\2\u0173\u0175\3\2\2\2\u0174\u0171\3\2\2\2\u0174\u0175\3\2\2\2"+
		"\u0175\u0176\3\2\2\2\u0176\u0177\7\'\2\2\u0177+\3\2\2\2\u0178\u017a\7"+
		"\f\2\2\u0179\u0178\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017b\3\2\2\2\u017b"+
		"\u017c\7T\2\2\u017c-\3\2\2\2\u017d\u017e\7T\2\2\u017e/\3\2\2\2\u017f\u0180"+
		"\7)\2\2\u0180\u0185\5(\25\2\u0181\u0182\7\65\2\2\u0182\u0184\5(\25\2\u0183"+
		"\u0181\3\2\2\2\u0184\u0187\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2"+
		"\2\2\u0186\u0188\3\2\2\2\u0187\u0185\3\2\2\2\u0188\u0189\7(\2\2\u0189"+
		"\61\3\2\2\2\u018a\u018f\5&\24\2\u018b\u018c\7\65\2\2\u018c\u018e\5&\24"+
		"\2\u018d\u018b\3\2\2\2\u018e\u0191\3\2\2\2\u018f\u018d\3\2\2\2\u018f\u0190"+
		"\3\2\2\2\u0190\63\3\2\2\2\u0191\u018f\3\2\2\2\u0192\u0193\7\t\2\2\u0193"+
		"\u0198\5\36\20\2\u0194\u0195\7\65\2\2\u0195\u0197\5\36\20\2\u0196\u0194"+
		"\3\2\2\2\u0197\u019a\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199"+
		"\65\3\2\2\2\u019a\u0198\3\2\2\2\u019b\u019c\7\13\2\2\u019c\u019d\58\35"+
		"\2\u019d\67\3\2\2\2\u019e\u01a4\5:\36\2\u019f\u01a0\5F$\2\u01a0\u01a1"+
		"\5:\36\2\u01a1\u01a3\3\2\2\2\u01a2\u019f\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4"+
		"\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a59\3\2\2\2\u01a6\u01a4\3\2\2\2"+
		"\u01a7\u01b4\5<\37\2\u01a8\u01b0\5L\'\2\u01a9\u01ac\5F$\2\u01aa\u01ad"+
		"\5:\36\2\u01ab\u01ad\5L\'\2\u01ac\u01aa\3\2\2\2\u01ac\u01ab\3\2\2\2\u01ad"+
		"\u01af\3\2\2\2\u01ae\u01a9\3\2\2\2\u01af\u01b2\3\2\2\2\u01b0\u01ae\3\2"+
		"\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b4\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b3"+
		"\u01a7\3\2\2\2\u01b3\u01a8\3\2\2\2\u01b4;\3\2\2\2\u01b5\u01b6\7)\2\2\u01b6"+
		"\u01b7\5:\36\2\u01b7\u01b8\7(\2\2\u01b8=\3\2\2\2\u01b9\u01ba\5\2\2\2\u01ba"+
		"?\3\2\2\2\u01bb\u01bc\5\2\2\2\u01bcA\3\2\2\2\u01bd\u01be\5\2\2\2\u01be"+
		"C\3\2\2\2\u01bf\u01c0\t\4\2\2\u01c0E\3\2\2\2\u01c1\u01c2\t\5\2\2\u01c2"+
		"G\3\2\2\2\u01c3\u01c4\7\33\2\2\u01c4I\3\2\2\2\u01c5\u01c9\7\20\2\2\u01c6"+
		"\u01c7\7\20\2\2\u01c7\u01c9\7\61\2\2\u01c8\u01c5\3\2\2\2\u01c8\u01c6\3"+
		"\2\2\2\u01c9K\3\2\2\2\u01ca\u01cb\5@!\2\u01cb\u01cc\5D#\2\u01cc\u01cd"+
		"\5> \2\u01cd\u01d9\3\2\2\2\u01ce\u01cf\5B\"\2\u01cf\u01d0\5H%\2\u01d0"+
		"\u01d1\5@!\2\u01d1\u01d2\7\r\2\2\u01d2\u01d3\5> \2\u01d3\u01d9\3\2\2\2"+
		"\u01d4\u01d5\5B\"\2\u01d5\u01d6\5J&\2\u01d6\u01d7\7\21\2\2\u01d7\u01d9"+
		"\3\2\2\2\u01d8\u01ca\3\2\2\2\u01d8\u01ce\3\2\2\2\u01d8\u01d4\3\2\2\2\u01d9"+
		"M\3\2\2\2\u01da\u01e0\5P)\2\u01db\u01dc\7\65\2\2\u01dc\u01df\5P)\2\u01dd"+
		"\u01df\5\\/\2\u01de\u01db\3\2\2\2\u01de\u01dd\3\2\2\2\u01df\u01e2\3\2"+
		"\2\2\u01e0\u01de\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1O\3\2\2\2\u01e2\u01e0"+
		"\3\2\2\2\u01e3\u01e4\5Z.\2\u01e4Q\3\2\2\2\u01e5\u01ee\5T+\2\u01e6\u01e8"+
		"\t\6\2\2\u01e7\u01e6\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9"+
		"\u01ea\7:\2\2\u01ea\u01ec\5Z.\2\u01eb\u01ed\5^\60\2\u01ec\u01eb\3\2\2"+
		"\2\u01ec\u01ed\3\2\2\2\u01ed\u01ef\3\2\2\2\u01ee\u01e7\3\2\2\2\u01ee\u01ef"+
		"\3\2\2\2\u01efS\3\2\2\2\u01f0\u01f7\5V,\2\u01f1\u01f2\7F\2\2\u01f2\u01f5"+
		"\5Z.\2\u01f3\u01f4\7K\2\2\u01f4\u01f6\5:\36\2\u01f5\u01f3\3\2\2\2\u01f5"+
		"\u01f6\3\2\2\2\u01f6\u01f8\3\2\2\2\u01f7\u01f1\3\2\2\2\u01f7\u01f8\3\2"+
		"\2\2\u01f8U\3\2\2\2\u01f9\u0202\5X-\2\u01fa\u01fc\t\7\2\2\u01fb\u01fd"+
		"\79\2\2\u01fc\u01fb\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe"+
		"\u01ff\7:\2\2\u01ff\u0200\5X-\2\u0200\u0201\5^\60\2\u0201\u0203\3\2\2"+
		"\2\u0202\u01fa\3\2\2\2\u0202\u0203\3\2\2\2\u0203W\3\2\2\2\u0204\u020e"+
		"\5Z.\2\u0205\u020a\7G\2\2\u0206\u0208\t\7\2\2\u0207\u0209\79\2\2\u0208"+
		"\u0207\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u020b\3\2\2\2\u020a\u0206\3\2"+
		"\2\2\u020a\u020b\3\2\2\2\u020b\u020c\3\2\2\2\u020c\u020d\7:\2\2\u020d"+
		"\u020f\5Z.\2\u020e\u0205\3\2\2\2\u020e\u020f\3\2\2\2\u020fY\3\2\2\2\u0210"+
		"\u0212\5\36\20\2\u0211\u0213\5h\65\2\u0212\u0211\3\2\2\2\u0212\u0213\3"+
		"\2\2\2\u0213\u0215\3\2\2\2\u0214\u0216\5 \21\2\u0215\u0214\3\2\2\2\u0215"+
		"\u0216\3\2\2\2\u0216\u0218\3\2\2\2\u0217\u0219\5`\61\2\u0218\u0217\3\2"+
		"\2\2\u0218\u0219\3\2\2\2\u0219\u022b\3\2\2\2\u021a\u021b\5p9\2\u021b\u021c"+
		"\5n8\2\u021c\u022b\3\2\2\2\u021d\u021e\7)\2\2\u021e\u021f\5N(\2\u021f"+
		"\u0220\7(\2\2\u0220\u022b\3\2\2\2\u0221\u0222\7J\2\2\u0222\u0223\5P)\2"+
		"\u0223\u0224\7H\2\2\u0224\u0225\79\2\2\u0225\u0226\7:\2\2\u0226\u0227"+
		"\5P)\2\u0227\u0228\7K\2\2\u0228\u0229\5:\36\2\u0229\u022b\3\2\2\2\u022a"+
		"\u0210\3\2\2\2\u022a\u021a\3\2\2\2\u022a\u021d\3\2\2\2\u022a\u0221\3\2"+
		"\2\2\u022b[\3\2\2\2\u022c\u022e\t\6\2\2\u022d\u022c\3\2\2\2\u022d\u022e"+
		"\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u0230\7:\2\2\u0230\u0232\5Z.\2\u0231"+
		"\u0233\5^\60\2\u0232\u0231\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u024c\3\2"+
		"\2\2\u0234\u0235\7F\2\2\u0235\u0238\5Z.\2\u0236\u0237\7K\2\2\u0237\u0239"+
		"\5:\36\2\u0238\u0236\3\2\2\2\u0238\u0239\3\2\2\2\u0239\u024c\3\2\2\2\u023a"+
		"\u023c\t\7\2\2\u023b\u023d\79\2\2\u023c\u023b\3\2\2\2\u023c\u023d\3\2"+
		"\2\2\u023d\u023e\3\2\2\2\u023e\u023f\7:\2\2\u023f\u0240\5X-\2\u0240\u0241"+
		"\5^\60\2\u0241\u024c\3\2\2\2\u0242\u0247\7G\2\2\u0243\u0245\t\7\2\2\u0244"+
		"\u0246\79\2\2\u0245\u0244\3\2\2\2\u0245\u0246\3\2\2\2\u0246\u0248\3\2"+
		"\2\2\u0247\u0243\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u0249\3\2\2\2\u0249"+
		"\u024a\7:\2\2\u024a\u024c\5Z.\2\u024b\u022d\3\2\2\2\u024b\u0234\3\2\2"+
		"\2\u024b\u023a\3\2\2\2\u024b\u0242\3\2\2\2\u024c]\3\2\2\2\u024d\u024e"+
		"\7K\2\2\u024e\u0254\5:\36\2\u024f\u0250\5F$\2\u0250\u0251\5:\36\2\u0251"+
		"\u0253\3\2\2\2\u0252\u024f\3\2\2\2\u0253\u0256\3\2\2\2\u0254\u0252\3\2"+
		"\2\2\u0254\u0255\3\2\2\2\u0255\u025a\3\2\2\2\u0256\u0254\3\2\2\2\u0257"+
		"\u0258\7<\2\2\u0258\u025a\5\60\31\2\u0259\u024d\3\2\2\2\u0259\u0257\3"+
		"\2\2\2\u025a_\3\2\2\2\u025b\u0260\5d\63\2\u025c\u025d\7\65\2\2\u025d\u025f"+
		"\5d\63\2\u025e\u025c\3\2\2\2\u025f\u0262\3\2\2\2\u0260\u025e\3\2\2\2\u0260"+
		"\u0261\3\2\2\2\u0261a\3\2\2\2\u0262\u0260\3\2\2\2\u0263\u026c\t\b\2\2"+
		"\u0264\u026a\7B\2\2\u0265\u026b\7:\2\2\u0266\u0267\7?\2\2\u0267\u026b"+
		"\7A\2\2\u0268\u0269\7@\2\2\u0269\u026b\7A\2\2\u026a\u0265\3\2\2\2\u026a"+
		"\u0266\3\2\2\2\u026a\u0268\3\2\2\2\u026b\u026d\3\2\2\2\u026c\u0264\3\2"+
		"\2\2\u026c\u026d\3\2\2\2\u026dc\3\2\2\2\u026e\u026f\7C\2\2\u026f\u0270"+
		"\5b\62\2\u0270\u0272\7)\2\2\u0271\u0273\5f\64\2\u0272\u0271\3\2\2\2\u0272"+
		"\u0273\3\2\2\2\u0273\u0274\3\2\2\2\u0274\u0275\7(\2\2\u0275\u027d\3\2"+
		"\2\2\u0276\u0277\7D\2\2\u0277\u0278\5b\62\2\u0278\u0279\7)\2\2\u0279\u027a"+
		"\5f\64\2\u027a\u027b\7(\2\2\u027b\u027d\3\2\2\2\u027c\u026e\3\2\2\2\u027c"+
		"\u0276\3\2\2\2\u027de\3\2\2\2\u027e\u0283\5.\30\2\u027f\u0280\7\65\2\2"+
		"\u0280\u0282\5.\30\2\u0281\u027f\3\2\2\2\u0282\u0285\3\2\2\2\u0283\u0281"+
		"\3\2\2\2\u0283\u0284\3\2\2\2\u0284g\3\2\2\2\u0285\u0283\3\2\2\2\u0286"+
		"\u0287\7E\2\2\u0287\u0288\7)\2\2\u0288\u0289\5j\66\2\u0289\u028a\7(\2"+
		"\2\u028ai\3\2\2\2\u028b\u0290\5l\67\2\u028c\u028d\7\65\2\2\u028d\u028f"+
		"\5l\67\2\u028e\u028c\3\2\2\2\u028f\u0292\3\2\2\2\u0290\u028e\3\2\2\2\u0290"+
		"\u0291\3\2\2\2\u0291k\3\2\2\2\u0292\u0290\3\2\2\2\u0293\u0294\7T\2\2\u0294"+
		"m\3\2\2\2\u0295\u0297\7\f\2\2\u0296\u0295\3\2\2\2\u0296\u0297\3\2\2\2"+
		"\u0297\u0298\3\2\2\2\u0298\u0299\7T\2\2\u0299o\3\2\2\2\u029a\u029b\7)"+
		"\2\2\u029b\u029c\5\26\f\2\u029c\u029d\7(\2\2\u029dq\3\2\2\2\u029e\u029f"+
		"\7U\2\2\u029fs\3\2\2\2\u02a0\u02a1\7U\2\2\u02a1u\3\2\2\2W\177\u008d\u008f"+
		"\u0097\u00a0\u00ae\u00b9\u00c1\u00c4\u00ca\u00ce\u00d0\u00d6\u00d8\u00dc"+
		"\u00e3\u00ea\u00f0\u00f2\u00f6\u00fd\u0101\u0108\u0116\u0118\u0121\u0128"+
		"\u012c\u0131\u0137\u0139\u013d\u0144\u0148\u014b\u0156\u015b\u0160\u0164"+
		"\u016a\u016f\u0174\u0179\u0185\u018f\u0198\u01a4\u01ac\u01b0\u01b3\u01c8"+
		"\u01d8\u01de\u01e0\u01e7\u01ec\u01ee\u01f5\u01f7\u01fc\u0202\u0208\u020a"+
		"\u020e\u0212\u0215\u0218\u022a\u022d\u0232\u0238\u023c\u0245\u0247\u024b"+
		"\u0254\u0259\u0260\u026a\u026c\u0272\u027c\u0283\u0290\u0296";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}