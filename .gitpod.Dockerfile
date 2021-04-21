FROM gitpod/workspace-postgres

# Install Angular CLI
RUN npm install -g @angular/cli@8.3.29

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
             && sdk install java 8.0.275.hs-adpt"
