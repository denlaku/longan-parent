(this.webpackJsonplongbi=this.webpackJsonplongbi||[]).push([[113],{1528:function(e,t,n){!function(e){"use strict";e.defineMode("vb",(function(t,n){function r(e){return new RegExp("^(("+e.join(")|(")+"))\\b","i")}var i=new RegExp("^[\\+\\-\\*/%&\\\\|\\^~<>!]"),o=new RegExp("^[\\(\\)\\[\\]\\{\\}@,:`=;\\.]"),a=new RegExp("^((==)|(<>)|(<=)|(>=)|(<>)|(<<)|(>>)|(//)|(\\*\\*))"),c=new RegExp("^((\\+=)|(\\-=)|(\\*=)|(%=)|(/=)|(&=)|(\\|=)|(\\^=))"),s=new RegExp("^((//=)|(>>=)|(<<=)|(\\*\\*=))"),u=new RegExp("^[_A-Za-z][_A-Za-z0-9]*"),d=["class","module","sub","enum","select","while","if","function","get","set","property","try","structure","synclock","using","with"],l=["else","elseif","case","catch","finally"],h=["next","loop"],m=["and","andalso","or","orelse","xor","in","not","is","isnot","like"],f=r(m),p=["#const","#else","#elseif","#end","#if","#region","addhandler","addressof","alias","as","byref","byval","cbool","cbyte","cchar","cdate","cdbl","cdec","cint","clng","cobj","compare","const","continue","csbyte","cshort","csng","cstr","cuint","culng","cushort","declare","default","delegate","dim","directcast","each","erase","error","event","exit","explicit","false","for","friend","gettype","goto","handles","implements","imports","infer","inherits","interface","isfalse","istrue","lib","me","mod","mustinherit","mustoverride","my","mybase","myclass","namespace","narrowing","new","nothing","notinheritable","notoverridable","of","off","on","operator","option","optional","out","overloads","overridable","overrides","paramarray","partial","private","protected","public","raiseevent","readonly","redim","removehandler","resume","return","shadows","shared","static","step","stop","strict","then","throw","to","true","trycast","typeof","until","until","when","widening","withevents","writeonly"],g=["object","boolean","char","string","byte","sbyte","short","ushort","int16","uint16","integer","uinteger","int32","uint32","long","ulong","int64","uint64","decimal","single","double","float","date","datetime","intptr","uintptr"],b=r(p),v=r(g),y=r(d),w=r(l),k=r(h),x=r(["end"]),I=r(["do"]);function E(e,t){t.currentIndent++}function L(e,t){t.currentIndent--}function z(e,t){if(e.eatSpace())return null;if("'"===e.peek())return e.skipToEnd(),"comment";if(e.match(/^((&H)|(&O))?[0-9\.a-f]/i,!1)){var r=!1;if((e.match(/^\d*\.\d+F?/i)||e.match(/^\d+\.\d*F?/)||e.match(/^\.\d+F?/))&&(r=!0),r)return e.eat(/J/i),"number";var d=!1;if(e.match(/^&H[0-9a-f]+/i)||e.match(/^&O[0-7]+/i)?d=!0:e.match(/^[1-9]\d*F?/)?(e.eat(/J/i),d=!0):e.match(/^0(?![\dx])/i)&&(d=!0),d)return e.eat(/L/i),"number"}return e.match('"')?(t.tokenize=function(e){var t=1==e.length;return function(r,i){for(;!r.eol();){if(r.eatWhile(/[^'"]/),r.match(e))return i.tokenize=z,"string";r.eat(/['"]/)}if(t){if(n.singleLineStringErrors)return"error";i.tokenize=z}return"string"}}(e.current()),t.tokenize(e,t)):e.match(s)||e.match(c)?null:e.match(a)||e.match(i)||e.match(f)?"operator":e.match(o)?null:e.match(I)?(E(0,t),t.doInCurrentLine=!0,"keyword"):e.match(y)?(t.doInCurrentLine?t.doInCurrentLine=!1:E(0,t),"keyword"):e.match(w)?"keyword":e.match(x)?(L(0,t),L(0,t),"keyword"):e.match(k)?(L(0,t),"keyword"):e.match(v)||e.match(b)?"keyword":e.match(u)?"variable":(e.next(),"error")}return e.registerHelper("hintWords","vb",d.concat(l).concat(h).concat(m).concat(p).concat(g)),{electricChars:"dDpPtTfFeE ",startState:function(){return{tokenize:z,lastToken:null,currentIndent:0,nextLineIndent:0,doInCurrentLine:!1}},token:function(e,t){e.sol()&&(t.currentIndent+=t.nextLineIndent,t.nextLineIndent=0,t.doInCurrentLine=0);var n=function(e,t){var n=t.tokenize(e,t),r=e.current();if("."===r)return"variable"===(n=t.tokenize(e,t))?"variable":"error";var i="[({".indexOf(r);return-1!==i&&E(0,t),-1!==(i="])}".indexOf(r))&&L(0,t)?"error":n}(e,t);return t.lastToken={style:n,content:e.current()},n},indent:function(e,n){var r=n.replace(/^\s+|\s+$/g,"");return r.match(k)||r.match(x)||r.match(w)?t.indentUnit*(e.currentIndent-1):e.currentIndent<0?0:e.currentIndent*t.indentUnit},lineComment:"'"}})),e.defineMIME("text/x-vb","vb")}(n(203))}}]);