<template>
  <div>
    <v-app id="inspire">
      <v-app id="inspire">
        <!-- left menu-->
        <v-navigation-drawer v-model="drawer" app clipped>
          <v-list dense>
            <!-- Dashboard Button -->
            <v-list-item @click=" navigation('DashBoard')">
              <v-list-item-action>
                <v-icon>mdi-view-dashboard</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                Dashboard
              </v-list-item-content>
            </v-list-item>
            <!-- Product Button -->
            <v-list-item @click=" navigation('Product')">
              <v-list-item-action>
                <v-icon>mdi-briefcase-plus</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                Produtos
              </v-list-item-content>
            </v-list-item>
            <!-- Costumers Button -->
            <v-list-item @click=" navigation('Employee')">
              <v-list-item-action>
                <v-icon>mdi-account-multiple</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                Clientes
              </v-list-item-content>
            </v-list-item>
            <!-- Category Button -->
            <v-list-item @click=" navigation('Category')">
              <v-list-item-action>
                <v-icon>mdi-source-branch</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                Categorias
              </v-list-item-content>
            </v-list-item>
            <!-- About Button -->
            <v-list-item @click=" navigation('About')">
              <v-list-item-action>
                <v-icon>mdi-information</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                Sobre
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-navigation-drawer>

        <!-- Principal container -->
        <v-main>
          <v-container fluid fill-height>
            <v-layout justify-center align-center>
              <v-card align-self-end>
                <router-view />
              </v-card>
            </v-layout>
          </v-container>
        </v-main>

        <v-app-bar app clipped-left>
          <!-- navbar top -->
          <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
          <v-toolbar-title>Automação </v-toolbar-title>
        </v-app-bar>
        <!-- Float Button -->
        <v-btn bottom color="red" name="add" fab fixed right large @click=" navigation('Sales')">
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </v-app>
    </v-app>
  </div>
</template>

<script>
export default {
  name: "App",
  props: {
    source: String,
  },

  data: () => ({
    drawer: false,
  }),
  mounted() {
    this.navigation('DashBoard')
  },
  methods: {
    navigation(where){
      this.$router.push({name: where})
    }
  },

  created() {
    this.$vuetify.theme.dark = true;
  },
};
</script>
