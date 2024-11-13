export default defineEventHandler(async (event)=>{
    const {public:{apiBaseUrl}} = useRuntimeConfig()
    const response = await $fetch(`${apiBaseUrl}/api/v1/view/news`)
    return response
})
