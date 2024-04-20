export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: ['@pinia/nuxt','@nuxtjs/tailwindcss','@vesp/nuxt-fontawesome'],
  css:["~/assets/style/main.scss"],
  app:{
    head:{
      htmlAttrs:{
        dir:'rtl'
      }
    }
  },
  fontawesome: {
    icons: {
      solid:['cart-shopping'],
    }
  }

})