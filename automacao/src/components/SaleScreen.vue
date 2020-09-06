<template  >
  <div id="SaleScreen" transition="scale-transition">
    <v-card>
      <v-card-text id="CardSaleScreen">
        <v-container>
          <!--Title-->
          <v-card-title cols="6" v-text="titleAssistance" />
          <v-row>
            <v-spacer></v-spacer>
            <v-col cols="3">
              <v-text-field
                v-model="sale.code"
                label="Número"
                persistent-hint
                return-object
                :rules="[rules.required]"
                id="code"
                disabled
              ></v-text-field>
            </v-col>
            <v-col cols="4">
              <v-autocomplete
                v-model="sale.employee"
                :items="employee"
                item-text="name"
                item-value="name"
                :label="`Cliente ${isEditing ? '- Add' : ''}`"
                persistent-hint
                return-object
                prepend-inner-icon="mdi-account-circle-outline"
                id="employee"
                autofocus
              >
                <template v-slot:append-outer>
                  <v-slide-x-reverse-transition mode="out-in">
                    <v-icon
                      :key="`icon-${isEditing}`"
                      :color="isEditing ? 'success' : 'info'"
                      @click="isEditing = !isEditing"
                      v-text="isEditing ? 'mdi-check-outline' : 'mdi-account-edit-outline'"
                    ></v-icon>
                  </v-slide-x-reverse-transition>
                </template>
              </v-autocomplete>
            </v-col>
            <!--Calendar-->
            <v-col cols="3">
              <v-menu
                v-model="menu2"
                :close-on-content-click="false"
                :nudge-right="0"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    locale="pt-br"
                    v-model="date"
                    label="Emissão"
                    prepend-inner-icon="mdi-calendar"
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker color="primary" locale="pt-br" v-model="date" @input="menu2 = false"></v-date-picker>
              </v-menu>
            </v-col>
          </v-row>
          <!--Inputs-->
          <v-row>
            <v-col cols="5">
              <v-select
                v-model="itemsDefault"
                :hint="`Est: ${itemsDefault.stock}, R$ ${itemsDefault.value}`"
                :items="items"
                item-text="name"
                item-value="name"
                label="Produto"
                persistent-hint
                return-object
                id="product"
              ></v-select>
            </v-col>
            <v-col cols="3">
              <v-text-field
                v-model="itemsDefault.amount"
                cols="12"
                prepend-inner-icon="mdi-cart-plus"
                value="1"
                label="Quantidade"
              ></v-text-field>
            </v-col>
            <v-col cols="3">
              <v-text-field
                v-model="itemsDefault.value"
                cols="12"
                prepend-inner-icon="mdi-currency-usd"
                label="Valor (R$)"
              ></v-text-field>
            </v-col>
            <v-icon color="success" @click="addItemList">mdi-plus-circle</v-icon>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-data-table :headers="headers" :items="itemList" sort-by="code" class="elevation-1">
                  <template v-slot:[`item.actionbutton`]="{ item }">
                  <v-icon class="mr-2" color="error" @click="deleteItem(item)">mdi-delete</v-icon>
                </template>
              </v-data-table>
            </v-col>
          </v-row>
          <v-row>
            <v-spacer></v-spacer>
            <v-btn :loading="isLoading" color="blue darken-1" text @click="verifyField">Gravar</v-btn>
          </v-row>
        </v-container>
      </v-card-text>
      <!--Add client-->
      <v-dialog v-model="isEditing" max-width="500px">
        <v-card>
          <v-card-title>Cadastrar Cliente ** EM DESENVOLVIMENTO **</v-card-title>
          <v-card-text>
            <v-row>
              <v-col cols="4">
                <v-text-field type="number" label="Código"></v-text-field>
              </v-col>
              <v-col>
                <v-text-field label="Nome"></v-text-field>
              </v-col>
            </v-row>
            <small class="grey--text">* Atalho para cadastro em construção.</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="isEditing = false" disabled>Gravar</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-card>
  </div>
</template>



<script>
import SaleAxios from "@/services/SaleAxios";
import ProductAxios from "@/services/ProductAxios";

export default {
  name: "SaleScreen",
  props: {
    employee: Array,
    items: Array,
  },
  data: () => ({
    radios: "pending",
    isEditing: false,
    date: new Date().toISOString().substr(0, 10),
    menu2: false,
    titleAssistance: "Venda",
    sale: {
      IssueDate: null,
      code: "",
      employee: {},
      salePrice: 0,
      productSale: [],
    },
    employeeDefault: {},
    itemsDefault: {
      code: null,
      id: "",
      name: "",
      stock: 0,
      value: 0,
      amount: 1,
      totalItem: 0,
    },
    totalSale: 0,
    itemList: [],
    headers: [
      {
        align: "start",
        sortable: false,
      },
      { text: "Código", value: "code" },
      { text: "Nome", value: "name" },
      { text: "Valor", value: "value" },
      { text: "Quantidade", value: "amount" },
      { text: "Vl. Total", value: "totalItem" },
      { text: "", value: "actionbutton", sortable: false },
    ],
    rules: {
      required: (value) => !!value || "Requerido",
    },
    isLoading: false,
  }),
  mounted() {
    this.loadCode();
  },
  methods: {
    verifyField() {
      if (this.sale.code == null) {
        document.getElementById("code").focus();
      } else if (this.sale.employee.name == null) {
        document.getElementById("employee").focus();
      } else if (this.itemList == "") {
        document.getElementById("product").focus();
      } else {
        this.isLoading = true;
        this.save();
        this.itemList = [];
      }
    },
    loadCode() {
      SaleAxios.MaxCode()
        .then((resposta) => {
          this.sale.code = resposta.data + 1;
        })
        .catch(() => {
          this.sale.code = 1;
        });
    },
    deleteItem(item) {
      const index = this.itemList.indexOf(item);
      this.$emit("deleteItem", item);
      confirm("Deseja remover o item?") && this.itemList.splice(index, 1);
    },
    addItemList() {
      this.itemsDefault.stock =
        this.itemsDefault.stock - this.itemsDefault.amount;
      this.itemsDefault.totalItem =
        this.itemsDefault.amount * this.itemsDefault.value;
      this.itemList.push(this.itemsDefault);
      this.itemsDefault = {};
    },
    save() {
      this.sale.IssueDate = this.date;
      this.sale.productSale = this.itemList;
      console.log(this.itemList);
      SaleAxios.SaveSale(this.sale)
        .then(
          // eslint-disable-next-line no-unused-vars
          (resposta) => {
            this.itemsDefault = {};

            this.$emit("saveItem");
            this.snackbarColor = "success";
            this.snackbarButtonColor = "white";
            this.snackbarText = "Incluído com sucesso";
            this.snackbar = true;
            this.isLoading = false;
            this.loadCode();
          }
        )
        .catch((error) => {
          console.log(error.console.data.errors);
          this.snackbarColor = "red";
          this.snackbarButtonColor = "white";
          this.snackbarText = "Erro ao incluir";
          this.snackbar = true;
          this.isLoading = false;
        });

      ProductAxios.SaveAll(this.itemList)
        .then(
          // eslint-disable-next-line no-unused-vars
          (resposta) => {}
        )
        .catch((e) => {
          console.log(e.response.data.errors);
        });
    },
  },
};
</script>