<script lang="ts">
export default {
  name: 'App',
  mounted() {
    this.$router.push('/login');
  }
}
</script>

<template>
  <router-view/>
</template>
