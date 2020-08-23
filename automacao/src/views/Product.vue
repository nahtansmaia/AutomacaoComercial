<template>
  <div class="Product">
    <ProductScreen
      :headers="headers"
      :items="items"
      :categoryList="category"
      v-on:deleteItem="DeleteItem"
      v-on:saveItem="SaveItem"
    ></ProductScreen>
  </div>
</template>


<script>
import ProductAxios from "@/services/ProductAxios";
import CategoryAxios from "@/services/CategoryAxios";
import ProductScreen from "@/components/ProductScreen";

export default {
  name: "Product",
  components: {
    ProductScreen,
  },
  data: () => ({
    headers: [
      {
        align: "start",
        sortable: false,
      },
      { text: "Código", value: "code" },
      { text: "Nome", value: "name" },
      { text: "Valor", value: "value" },
      { text: "Estoque", value: "stock" },
      { text: "Categoria", value: "category.name" },
      { text: "", value: "actionbutton", sortable: false },
    ],
    items: [],
    category: [],
  }),
  mounted() {
    this.ListItemAxios();
  },
  methods: {
    ListItemAxios() {
      ProductAxios.ListProduct().then((resposta) => {
        this.items = resposta.data;
      });
      this.ListCategoryAxios();
    },
    ListCategoryAxios() {
      CategoryAxios.ListCategory().then((resposta) => {
        this.category = resposta.data;
      });
    },

    DeleteItem(deleteItem) {
      if (confirm("Deseja remover o produto " + deleteItem.name)) {
        ProductAxios.DeleteProduct(deleteItem)
          // eslint-disable-next-line no-unused-vars
          .then((resposta) => {
            this.ListItemAxios();
          })
          .catch((e) => {
            this.error = e.response.data.errors;
          });
      }
    },
    SaveItem() {
      this.ListItemAxios();
    },
  },
};
</script>