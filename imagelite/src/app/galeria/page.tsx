"use client"

import { Template, ImageCard, Button, InputText} from '@/components'
import {useImageService} from '@/resources/image/image-service'
import {Image} from '@/resources/image/image-resource'
import { useState } from 'react'
import Link from 'next/link'

export default function GaleriaPage(){
    
    const userService = useImageService();
    const [images, setImages] = useState<Image[]>([])
    const [query, setQuery] = useState<string>('')
    const [extension, setExtension]= useState<string>('')

        async function searchImage(){
            console.log("Valor digitado:",query)
           const result = await userService.buscar(query, extension);
           setImages(result);
        }
        function renderImageCard(image: Image){
            return (
                <ImageCard key={image.url} nome={image.name} src={image.url} tamanho={image.size} 
                extension={image.extension} dataUpload={image.uploandDate}/>
            )
        }

        function renderImageCards() {
           return images.map(renderImageCard)
        }
    return(
        
        <Template>
            <section className='flex flex-col items-center justify-center my-5'>
                <div className='flex space-x-4'>
                    <InputText placeholder='Type Name or Tags'
                     onChange={event => setQuery(event.target.value)}/>

                        <select onChange={event => setExtension(event.target.value)} className='border px-4 py-2 rounded-lg text-gray-900'>
                            <option value="">All formats</option>
                            <option value="PNG">PNG</option>
                            <option value="JPEG">JPEG</option>
                            <option value="GIF">GIF</option>
                        </select>
                        <Button style='bg-blue-500 hover:bg-blue-300' label='Search' onClick={searchImage}/>
                        <Link href="/formulario">
                        <Button style='bg-yellow-500 hover:bg-yellow-300' label='Add New'/>

                      
                        </Link>
                        
                </div>
            </section>
            <section className="grid grid-cols-3 gap-8">

            {
               renderImageCards()
            }
            
            </section>
            
        </Template>
    )
}