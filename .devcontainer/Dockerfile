FROM mcr.microsoft.com/devcontainers/base:ubuntu-22.04
RUN apt-get update \
 && apt-get install openjdk-8-jdk openjdk-17-jdk maven -y \
 && apt-get clean
COPY --from=iordaniskostelidis/linkedin-data-importer /root/.m2/repository/gr/ihu/ict/linkedin-data-importer /home/vscode/.m2/repository/gr/ihu/ict/linkedin-data-importer
COPY --from=iordaniskostelidis/zotero-publications-importer /root/.m2/repository/gr/ihu/ict/zotero-publications-importer /home/vscode/.m2/repository/gr/ihu/ict/zotero-publications-importer
RUN chown vscode:vscode -R /home/vscode/.m2