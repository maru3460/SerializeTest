# シリアライズのテスト(+DIコンテナ)
JDK11がインストールされているPCで実行 

## 機能
シリアライズを使った簡単なログイン、ログアウト機能  

## 改善点
・例外処理  
・ユーザー名とパスワードを使ってログインできるようにする  
・cereatedAt, updatedAtの追加  
・特定のユーザーだけでなく、任意のユーザーを管理するクラスを作る。(Containerのsearch, createUserを移す)    

## 名前  
・logio: login, logout  
・BL: bisiness logic  
・DA: data access  
・DI: Dependency Injection  
・Pre: Presentation  

## 実行
mkdir modules

javac -d classes/logioBL/ src/logioBL/module-info.java src/logioBL/logioBL/**/*.java  
jar cvf modules/logioBL.jar -C classes/logioBL .  

javac --module-path modules -d classes/logioDA/ src/logioDA/module-info.java src/logioDA/logioDA/**/*.java  
jar cvf modules/logioDA.jar -C classes/logioDA .  

javac --module-path modules -d classes/logioDI/ src/logioDI/module-info.java src/logioDI/logioDI/**/*.java  
jar cvf modules/logioDI.jar -C classes/logioDI .  

javac --module-path modules -d classes/logioPre/ src/logioPre/module-info.java src/logioPre/logioPre/**/*.java  
jar cvf modules/logioPre.jar -C classes/logioPre .  

java --module-path modules --module logioPre/logioPre.presentation.Main  

## javadocの作成  
javadoc -d docs/logioBL $(find src/logioBL -name "*.java")

javadoc --module-path modules --add-modules logioBL -d docs/logioDA $(find src/logioDA -name "*.java") 

javadoc --module-path modules --add-modules logioBL,logioDA -d docs/logioDI $(find src/logioDI -name "*.java")  

javadoc --module-path modules --add-modules logioBL,logioDI -d docs/logioPre $(find src/logioPre -name "*.java")  

※src/logioBL/logioBL/**/*.java と $(find src/logioBL -name "*.java")はどちらでもOK  