<template>
  <div class="row">
    <h2 class="headertekst">DashBoard</h2>
    <DashboardChartScreen />
    <DashboardListSaleScreen 
    :items="itemsSaleList" 
    :headers="headersSaleList"/>
    <DashboardCardScreen />
  </div>
</template>

<script>
import DashboardChartScreen from "@/components/DashboardChartScreen";
import DashboardListSaleScreen from "@/components/DashboardListSaleScreen";
import DashboardCardScreen from "@/components/DashboardCardScreen";
import SaleAxios from "@/services/SaleAxios";

export default {
  components: {
    DashboardChartScreen,
    DashboardListSaleScreen,
    DashboardCardScreen,
  },
  data: () => ({
    headersSaleList: [
      {
        text: "Nr.Documento",
        align: "left",
        value: "code",
        groupable: false,
      },
      { text: "Cliente", value: "employee.name", align: "left" },
      { text: "Emissão", value: "IssueDate", align: "center" },
      { text: "Valor", value: "salePrice", align: "right"},
      { text: "", value: "data-table-expand" },
    ],
    itemsSaleList: [],
  }),
  mounted() {
    this.ListItemAxios();
  },
  methods: {
    ListItemAxios() {
      SaleAxios.ListSale().then((resposta) => {
        this.itemsSaleList = resposta.data;
      });
    },
  },
};
</script>


<style lang="scss">
h2.headertekst {
  text-align: center;
  width: 100%;
}
</style>