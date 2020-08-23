<template>
  <div class="Category">
    <CategoryScreen 
    :headers="headers" 
    :items="items" 
    v-on:deleteItem="DeleteItem"
    v-on:saveItem="SaveItem"></CategoryScreen>
  </div>
</template>


<script>
import CategoryAxios from "@/services/CategoryAxios";
import CategoryScreen from "@/components/CategoryScreen";

export default {
  name: "Category",
  components: {
    CategoryScreen,
  },
  data: () => ({
    headers: [
      {
        align: "start",
        sortable: false,
      },
      { text: "Código", value: "code" },
      { text: "Nome", value: "name" },
      { text: "", value: "actionbutton", sortable: false },
    ],
    items: [],
  }),
  mounted() {
    this.ListItemAxios();
  },
  methods: {
    ListItemAxios() {
      CategoryAxios.ListCategory().then((resposta) => {
        this.items = resposta.data;
      });
    },

    DeleteItem(deleteItem) {
      if (confirm("Deseja remover a categoria " + deleteItem.name)) {
        CategoryAxios.DeleteCategory(deleteItem)
          // eslint-disable-next-line no-unused-vars
          .then((resposta) => {
            this.ListItemAxios();
          })
          .catch((e) => {
            this.error = e.response.data.errors;
          });
      }
    },
    SaveItem(){
      this.ListItemAxios();
    }
  },
};
</script>