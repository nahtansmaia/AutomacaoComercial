<template>
  <div class="row">
    <v-col class="mx-auto elevation-20 home-box">
      <BarChart :chart-data="barChartData" :height="200" v-if="loadedBar"></BarChart>
    </v-col>
    <v-col class="mx-auto elevation-20 home-box">
      <line-chart :chart-data="lineChartData" :height="200" v-if="loadedLine"></line-chart>
    </v-col>
  </div>
</template>

<script>
import LineChart from "./Charts/LineChart.js";
import BarChart from "./Charts/BarChart.js";
import SaleAxios from "@/services/SaleAxios";

export default {
  components: {
    LineChart,
    BarChart,
  },
  data() {
    return {
      loadedBar: false,
      loadedLine: false,
      lineChartData: {},
      barChartData: {},
    };
  },
  mounted() {
    this.loadedBar = false;
    this.loadedLine = false;
    this.ChartLineLoad();
    this.ChartBarLoad();
  },
  methods: {
    ChartLineLoad() {
      SaleAxios.ListSale().then((resposta) => {
        this.lineChartData = resposta.data;

        var result = [];
        this.lineChartData.reduce(function (res, value) {
          if (!res[value.IssueDate]) {
            res[value.IssueDate] = { IssueDate: value.IssueDate, salePrice: 0 };
            result.push(res[value.IssueDate]);
          }
          res[value.IssueDate].salePrice += value.salePrice;
          return res;
        }, {});
        this.lineChartData = result
          .slice().sort((a, b) => new Date(b.IssueDate) - new Date(a.IssueDate))
          .filter((r, i) => i < 7);
        this.RenderChartLine();
      });
    },
    ChartBarLoad() {
      SaleAxios.ListSale().then((resposta) => {
        this.barChartData = resposta.data;
        var result = [];
        this.barChartData.reduce(function (res, value) {
          if (!res[value.employee.name]) {
            res[value.employee.name] = {
              name: value.employee.name,
              salePrice: 0,
            };
            result.push(res[value.employee.name]);
          }
          res[value.employee.name].salePrice += value.salePrice;
          return res;
        }, {});
        this.barChartData = result
          .sort(function (a, b) {
            return a.salePrice - b.salePrice;
          })
          .filter((r, i) => i < 10);
        this.RenderChartBar();
      });
    },
    RenderChartLine() {
      this.lineChartData = {
        labels: this.lineChartData.map((item) => item.IssueDate),
        datasets: [
          {
            label: "Vendas por dia",
            backgroundColor: ["#fc6565", "#e85656", "#cf4040", "#b03030", "#9e2424", "#871a1a", "#751414"],
            borderColor:["#fc6565", "#e85656", "#cf4040", "#b03030", "#9e2424", "#871a1a", "#751414"],
            borderWidth: 3,
            spanGaps: true,
            showLine:true,
            fill:false,
            data: this.lineChartData.map((item) => item.salePrice),
          },
        ],
      };
      this.loadedLine = true;
    },
    RenderChartBar() {
      this.barChartData = {
        labels: this.barChartData.map((item) => item.name),
        datasets: [
          {
            label: "Top 10 clientes por valor",
            backgroundColor: ["#FC6565", "#e85656", "#cf4040", "#b03030", "#9e2424", "#871a1a", "#751414", "#690f0f", "#5c0909", "420303"],
            data: this.barChartData.map((item) => item.salePrice),
          },
        ],
      };
      this.loadedBar = true;
    },
  },
};
</script>

<style lang="scss" scoped>
.row {
  padding: 2%;
  height: 300px;
  padding-top: 0.5%;
  .home-box {
    width: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 0%;
  }
}
</style>