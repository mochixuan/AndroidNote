import React,{Component} from 'react'
import {
    View,
    Text,
    SafeAreaView,
    StyleSheet,
    Image
} from 'react-native'

export default class OnePage extends Component {

    render() {
        return (
            <SafeAreaView style={styles.container}>
                <View style={styles.swipe}>
                    <Text style={styles.text}>我是正常加载的React Native</Text>
                    <Image
                        style={styles.img}
                        source={{uri: 'http://n.sinaimg.cn/photo/700/w1000h500/20190428/zY0J-hwfpcxm8040335.jpg'}}
                    />
                    <Image
                        style={styles.img}
                        source={{uri: 'http://n.sinaimg.cn/photo/transform/700/w1000h500/20190428/LIfV-hwfpcxm7117720.jpg'}}
                    />
                    <Image
                        style={styles.img}
                        source={{uri: 'http://n.sinaimg.cn/photo/700/w1000h500/20190424/pZyD-hvvuiyn8021956.jpg'}}
                    />
                    <Image
                        style={styles.img}
                        source={{uri: 'http://n.sinaimg.cn/photo/700/w1000h500/20190428/MMtF-hwfpcxm7113878.jpg'}}
                    />
                </View>
            </SafeAreaView>
        )
    }

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#000'
    },
    swipe: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',

    },
    text: {
        fontSize: 18,
        color: '#ffffff',
        padding: 20,
        backgroundColor: '#16A085',
        borderRadius: 30
    },
    img: {
        width: 200,
        height: 200*9/16,
        marginTop: 10,
        borderRadius: 10,
    }
})
