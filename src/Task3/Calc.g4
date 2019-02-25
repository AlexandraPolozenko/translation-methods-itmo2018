grammar Calc;

options {
    language = Java;
}

@header{
	import java.util.HashMap;
}

@members{
	HashMap<String, Integer> memory = new HashMap<>();

	int pow(int a, int b) {
      int res = 1;
      for (int i = 0; i < b; i++) {
        res *= a;
      }

      return res;
    }
}

ID
    : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
    ;

INT
    :	'0'..'9'+
    ;

WS: [ \n\t\r]+ -> skip;

calc
    : statement+
    ;

statement
    : expr ';' { System.out.println($expr.val); }
    | ID '=' expr ';' {
        memory.put($ID.text, $expr.val);
        System.out.println($ID.text + " = " + $expr.val);
    }
    ;

expr returns[int val]
    : resFirst = multExpression { $val = $resFirst.val; }
        ('+' resFollow = multExpression { $val += $resFollow.val; }
        |'-' resFollow = multExpression { $val -= $resFollow.val; } )*
    ;

multExpression returns[int val]
	: resFirst = pow { $val = $resFirst.val; }
          ('*' resFollow = multExpression { $val *= $resFollow.val; }
          |'/' resFollow = multExpression { $val /= $resFollow.val; } )*
	;

pow returns[int val]
: resFirst = atom { $val = $resFirst.val; }
 ('^' resFollow = pow { $val = pow($resFirst.val, $resFollow.val); })*
;

atom returns[int val]
	: ID { $val = memory.get($ID.text); }
	| INT { $val = Integer.parseInt($INT.text); }
	| '(' expr ')' { $val = $expr.val; }
	;