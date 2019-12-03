# ! /bin/bash

echo "Meu primeiro shell script"
echo "mostra o diretório corrente" | 
pwd |
echo "lista os diretórios e arquivos do diretório de forma detalhada" |
ls |
which java | grep -q /usr/bin/java
if [ $? = 0 ]
	then java java -jar TelaLogin-1.0-SNAPSHOT-jar-with-dependencies.jar
else "JDK não está instalado, por favor faça o download para poder executar nosso projeto"
fi
