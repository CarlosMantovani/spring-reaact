"use client"

import { Template, ImageCard } from '@/components'
import {useImageService} from '@/resources/image/image-service'
import {Image} from '@/resources/image/image-resource'
import { useState } from 'react'


export default function GaleriaPage(){
 
    const userService = useImageService();
    const [images, setImages] = useState<Image[]>([])

    async function searchImage(){
           const result = await userService.buscar();
           setImages(result);
           console.table(images);
    }
        function renderImageCard(image: Image){
            return (
                <ImageCard nome={image.name} src={image.url} tamanho={image.size} dataUpload={image.uploadDate}/>
            )
        }

        function renderImageCards() {
           return images.map(renderImageCard)
        }
    return(
        
        <Template>
        <button className='bg-gray-500' onClick={searchImage}>Clique para mudar</button>
            <section className="grid grid-cols-3 gap-8">

            {
               renderImageCards()
            }
            
            </section>
            
        </Template>
    )
}