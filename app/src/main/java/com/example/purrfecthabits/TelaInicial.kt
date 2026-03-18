package com.julia.purrfecthabits
import com.example.purrfecthabits.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.purrfecthabits.EstadoTelaInicial

val CorFundoApp = Color(0xFF8FAFC) // Um cinza levemente mais azulado/limpo
val CorCianoPrimario = Color(0xFF20A4C4)
val CorVerdeProgresso = Color(0xFF45B88D)
val CorFundoProgresso = Color(0xFFE2E8F0)
val CorTextoEscuro = Color(0xFF1E293B)
val CorTextoCinza = Color(0xFF64748B)

@Composable
fun TelaInicial(
    estado: EstadoTelaInicial = EstadoTelaInicial(),
    aoNavegarParaHabitos: () -> Unit,
    aoNavegarParaEstatisticas: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CorFundoApp)
            .padding(horizontal = 28.dp, vertical = 32.dp), // Mais respiro nas laterais
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- CABEÇALHO ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Purrfect Habits",
                fontSize = 20.sp, // Um pouco menor para ficar elegante
                fontWeight = FontWeight.ExtraBold,
                color = CorCianoPrimario, // Usando a cor do tema na marca
                letterSpacing = (-0.5).sp
            )
            // Ícone com um fundo circular suave
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CorCianoPrimario.copy(alpha = 0.1f))
                    .clickable { aoNavegarParaEstatisticas() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Estatísticas",
                    tint = CorCianoPrimario,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // --- SAUDAÇÃO ---
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Olá, Julia! 👋", // Personalização fingida para o design
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = CorTextoEscuro
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "O seu gato está esperando por você.",
                fontSize = 16.sp,
                color = CorTextoCinza
            )
        }

        Spacer(modifier = Modifier.weight(0.8f)) // Espaço flexível

        // --- AVATAR DO GATO (Destaque Central) ---
        Box(
            modifier = Modifier
                .size(240.dp) // Um pouco maior
                .shadow(20.dp, CircleShape, spotColor = CorCianoPrimario) // Sombra suave
                .clip(CircleShape)
                .background(Color.White)
                .border(width = 4.dp, color = Color.White, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                // Use o seu R.drawable.feliz, certifique-se de que o import está correto
                painter = painterResource(id = R.drawable.feliz),
                contentDescription = "Gato virtual feliz",
                modifier = Modifier.size(180.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // --- SEÇÃO DE PROGRESSO ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Progresso de Hoje",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CorTextoEscuro
                    )
                    Text(
                        text = "${estado.habitosConcluidos}/${estado.totalHabitos}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = CorCianoPrimario
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Barra de progresso moderna com preenchimento gradual
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .clip(CircleShape)
                        .background(CorFundoProgresso)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = estado.porcentagemProgresso)
                            .fillMaxHeight()
                            .clip(CircleShape)
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(CorVerdeProgresso, Color(0xFF6EDBA1))
                                )
                            )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- BOTÃO PRINCIPAL ---
        Button(
            onClick = aoNavegarParaHabitos,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .shadow(12.dp, RoundedCornerShape(20.dp), clip = false),
            colors = ButtonDefaults.buttonColors(containerColor = CorCianoPrimario),
            shape = RoundedCornerShape(20.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = "Ver meus hábitos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
@Preview(showBackground = true)

@Composable

fun TelaInicialPreview() {

    TelaInicial(
        estado = EstadoTelaInicial(habitosConcluidos = 3, totalHabitos = 5),
        aoNavegarParaHabitos = {},
        aoNavegarParaEstatisticas = {}
    )

}