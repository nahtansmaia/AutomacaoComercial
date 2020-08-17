<template>
  <div class="About">
    <v-card>
      <v-card-title class="grey darken-3">Sobre</v-card-title>
      <v-container>
        <table>
          <thead>
            <h3>Automação</h3>
            <li>Cadastro de produtos.</li>
            <li>Cadastro de categorias.</li>
            <li>Cadastro de clientes.</li>
            <li>Vendas</li>
            <li>DashBoard</li>
          </thead>
          <tbody>
            <br />Sugestões? Entre em contato!
          </tbody>
          <footer>
            <div style='text-align:right'>
              <br />
              <br />
              <v-btn text color="white" rounded key="btnGithub">
                <a target="_blank" href="https://github.com/nahtansmaia/">
                  <v-icon>mdi-github</v-icon>
                </a>
              </v-btn>
              <v-btn text rounded color="primary">
                <a target="_blank" href="https://twitter.com/nahtanmaia">
                  <v-icon>mdi-twitter</v-icon>
                </a>
              </v-btn>
              Desenvolvido por Nathan Maia.
            </div>
          </footer>
        </table>
      </v-container>
      <v-card-actions>
        <v-btn @click=" navigation('DashBoard')" text color="primary">Fechar</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<style>
a:link {
  color: white;
  text-decoration: none;
}
</style>

<script>
export default {
  methods: {
    navigation(where) {
      this.$router.push({ name: where });
    },
  },
};
</script>
