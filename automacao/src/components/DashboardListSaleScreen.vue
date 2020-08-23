<template>
  <div class="mx-auto elevation-20">
    <template>
      <v-data-table
        id="table"
        :headers="headers"
        :items="items"
        :expanded.sync="expanded"
        item-key="code"
        show-expand
        class="elevation-1"
        dense
        height="400px"
        sort-by="code"
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>Vendas</v-toolbar-title>
          </v-toolbar>
        </template>
        <template v-slot:expanded-item="{ headers, item  }">
          <td :colspan="headers.length">
            <table v-for="(productSale, index) in item.productSale" :key="index">
              <tr>
                <th>Produto:</th>
                <td>{{ productSale.name }}</td>
                <v-spacer></v-spacer>
                <th align="center">Quantidade:</th>
                <td>{{ productSale.amount }}</td>
                <v-spacer></v-spacer>
                <th>Valor total:</th>
                <td>R$ {{ productSale.totalItem }}</td>
              </tr>
            </table>
          </td>
        </template>
      </v-data-table>
    </template>
  </div>
</template>

<script>
export default {
  props: {
    items: Array,
    headers: Array,
  },
  data: () => ({
    expanded: [],
  }),
};
</script>

<style lang="scss" scoped>
.DashBoard {
  width: 60%;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 0px solid darkslategray;
  border-radius: 2%;
  margin: 20px;
  margin-top: 0%;
}
</style>