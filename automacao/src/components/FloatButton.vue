<template>
  <v-speed-dial
    v-model="fab"
    direction="top"
    :bottom="bottom"
    :right="right"
    fixed
    :transition="transition"
    leave-absolute
  >
    <template v-slot:activator>
      <v-btn  bottom color="red" name="add" fab fixed right large @click=" navigation('Sale')">
        <v-icon v-if="fab">mdi-close</v-icon>
        <v-icon v-else>mdi-plus</v-icon>
      </v-btn>
    </template>
    <v-expand-x-transition>
      <v-card v-show="fab" height="100" width="100" class="shrink">
        <v-btn small color="indigo">Venda</v-btn>
        <v-btn small color="red">Compra</v-btn>
      </v-card>
    </v-expand-x-transition>
  </v-speed-dial>
</template>

<script>
export default {
  name: "floatButton",
  data: () => ({
    fab: false,
    right: true,
    bottom: true,
    transition: "slide-x-reverse-transition",
  }),
  methods: {
    navigation(where) {
      this.$router.push({ name: where });
    },
  },
};
</script>

<style>
#create .v-btn--floating {
  position: relative;
}
</style>