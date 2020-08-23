<template>
  <div class="Employee">
    <EmployeeScreen 
    :headers="headers" 
    :items="items" 
    v-on:deleteItem="DeleteItem"
    v-on:saveItem="SaveItem"></EmployeeScreen>
  </div>
</template>


<script>
import EmployeeAxios from "@/services/EmployeeAxios";
import EmployeeScreen from "@/components/EmployeeScreen";

export default {
  name: "Employee",
  components: {
    EmployeeScreen,
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
      EmployeeAxios.ListEmployee().then((resposta) => {
        this.items = resposta.data;
      });
    },

    DeleteItem(deleteItem) {
      if (confirm("Deseja remover o cliente " + deleteItem.name)) {
        EmployeeAxios.DeleteEmployee(deleteItem)
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