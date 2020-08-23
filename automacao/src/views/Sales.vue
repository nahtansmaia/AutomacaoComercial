<template>
  <div class="Sales">
    <SaleScreen 
    :employee="employee" 
    :items="product" 
    :IsOpen="isOpen"
     v-on:saveItem="SaveItem"></SaleScreen>
  </div>
</template>

<script>
import SaleScreen from "@/components/SaleScreen.vue";
import EmployeeAxios from "@/services/EmployeeAxios";
import ProductAxios from "@/services/ProductAxios";

export default {
  components: {
    SaleScreen,
  },
  data: () => ({
    employee: [],
    product: [],
  }),
  mounted() {
    this.isOpen();
    this.LoadSale();
  },
  methods: {
    LoadSale() {
      this.ListEmployeeAxios();
      this.ListProductAxios();
      
    },
    ListEmployeeAxios() {
      EmployeeAxios.ListEmployee().then((resposta) => {
        this.employee = resposta.data;
      });
    },
    ListProductAxios() {
      ProductAxios.ListProduct().then((resposta) => {
        this.product = resposta.data;
      });
    },
    isOpen() {
      this.isOpen = true;
    },
    SaveItem(){
      this.LoadSale();  
    }
  },
};
</script>