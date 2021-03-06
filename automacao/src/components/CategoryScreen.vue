<template>
  <div class="CategoryScreen">
    <v-data-table :headers="headers" :items="items" sort-by="code" class="elevation-1">
      >
      <template v-slot:top>
        <v-toolbar flat color="dark">
          <v-toolbar-title>Categorias</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="600px">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="primary" dark v-bind="attrs" v-on="on">Novo</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>
              <v-card-text id="CardTextFilter">
                <v-container>
                  <v-row>
                    <v-col cols="4">
                      <v-text-field
                        :rules="[rules.required]"
                        required
                        type="number"
                        label="Código"
                        v-model="editedItem.code"
                        disabled
                        id="code"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="8">
                      <v-text-field
                        :rules="[rules.required, rules.maxLenght]"
                        :counter="50"
                        autofocus
                        required
                        type="text"
                        label="Nome"
                        v-model="editedItem.name"
                        id="name"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="close">Cancelar</v-btn>
                  <v-btn color="blue darken-1" text @click="verifyField">Salvar</v-btn>
                </v-card-actions>
              </v-card-text>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>
      <template v-slot:[`item.actionbutton`]="{ item }">
        <v-icon class="mr-2" color="error" @click="deleteItem(item)">mdi-delete</v-icon>
        <v-icon key="editBtn" class="mr-2" color="blue darken-1" @click="editItem(item)">mdi-pencil</v-icon>
      </template>
    </v-data-table>
    <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="SnackBarTimeout">
      {{ snackbarText }}
      <template v-slot:action="{ attrs }">
        <v-btn :color="snackbarButtonColor" text v-bind="attrs" @click="snackbar = false">Fechar</v-btn>
      </template>
    </v-snackbar>
  </div>
</template>


<script>
import CategoryAxios from "@/services/CategoryAxios";

export default {
  name: "CategoryScreen",
  props: ["headers", "items"],

  data: () => ({
    dialog: false,
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    snackbarButtonColor: "",
    SnackBarTimeout: "2000",
    editedItem: {
      code: null,
      name: "",
      id: "",
    },
    defaultItem: {},
    rules: {
      required: (value) => !!value || "Requerido",
      maxLenght: v => (v && v.length <= 50) || 'Máximo de 50 caracteres',
    },
  }),
  computed: {
    formTitle() {
      return this.editedItem.id === "" ? "Novo" : "Editar";
    },
  },
   mounted() {
    this.loadCode();
  },
  methods: {
    verifyField() {
      if (this.editedItem.code == null) {
        document.getElementById("code").focus();
      } else if (this.editedItem.name == "" || this.editedItem.name.length > 50) {
        document.getElementById("name").focus();
      } else {
        this.isLoading = true;
        this.save();
      }
    },
    deleteItem(item) {
      const index = this.items.indexOf(item);
      if (this.items.splice(index, 1)) {
        this.$emit("deleteItem", item);
        this.snackbarColor = "red";
        this.snackbarButtonColor = "white";
        this.snackbarText = "Excluído com sucesso";
        this.snackbar = true;
      }
    },
    editItem(item) {
      this.$emit("editItem", item);
      this.editedItem = item;
      this.dialog = true;
    },
    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedItem.id = "";
      });
    },
    loadCode() {
      CategoryAxios.MaxCode()
        .then((resposta) => {
          this.editedItem.code = resposta.data + 1;
          
        })
        .catch(() => {
          this.editedItem.code = 1;
        });
    },
    save() {
      if (!this.editedItem.id) {
        CategoryAxios.SaveCategory(this.editedItem)
          .then(
            // eslint-disable-next-line no-unused-vars
            (resposta) => {
              this.editedItem = {};
              this.editedItem.id = "";
              this.$emit("saveItem");
              this.dialog = false;
              this.snackbarColor = "success";
              this.snackbarButtonColor = "white";
              this.snackbarText = "Incluído com sucesso";
              this.snackbar = true;
            }
          )
          .catch((error) => {
            console.log(error.console.data.errors);
            this.snackbarColor = "red";
            this.snackbarButtonColor = "white";
            this.snackbarText = "Erro ao incluir";
            this.snackbar = true;
          });
      } else {
        CategoryAxios.UpdateCategory(this.editedItem)
          .then(
            // eslint-disable-next-line no-unused-vars
            (resposta) => {
              this.editedItem = {};
              this.editedItem.id = -1;
              this.$emit("saveItem");
              this.dialog = false;
              this.snackbarColor = "success";
              this.snackbarButtonColor = "white";
              this.snackbarText = "Editado com sucesso";
              this.snackbar = true;
            }
          )
          .catch((e) => {
            console.log(e.response.data.errors);
            this.snackbarColor = "red";
            this.snackbarButtonColor = "white";
            this.snackbarText = "Erro ao editar";
            this.snackbar = true;
          });
      }
    },
  },
};
</script>