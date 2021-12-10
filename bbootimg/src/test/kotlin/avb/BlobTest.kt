// Copyright 2021 yuyezhong@gmail.com
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package avb

import avb.alg.Algorithms
import avb.blob.AuxBlob
import cfig.helper.CryptoHelper
import cfig.helper.Helper
import org.apache.commons.codec.binary.Hex
import org.bouncycastle.asn1.pkcs.RSAPrivateKey
import org.junit.Assert.assertEquals
import org.junit.Test

class BlobTest {
    @Test
    fun testEncodedKey2048() {
        val keyStr =
            "2d2d2d2d2d424547494e205253412050524956415445204b45592d2d2d2d2d0a4d4949456f77494241414b4341514541786c56523354496b6f75414f7648373976614a54674668706676564b514965566b46525a5056584b2f7a5930477672680a344a4171476a4a6f572f50667251763573644433367174484833612b4735684c5a364e692b742f6d74666a7563785a66754c4743336b6d4a3154335871454b5a0a67585849324952377656536f496d5245764451474544794a7774487a4c414e6c6b624767306367685668575a5343416e644f3842656e616c43327639342f72740a44666b50656b48366467553353663430543073425365535939346d4f7a5461714f52327066563172576c4c5264576d6f33337a654842763532526c627430644d0a755841757265585769487a746b6d35474342433164674d2b4361784e74697a4e45674339314b63443078755243434d325778482b72316c70737a79494a4463740a596272466d5645596c2f6b6a517061666879374e736b316671535479526472695a53596d5451494441514142416f49424151432b6b4a6761437558387759416e0a5358575130666d645a6c586e4d4e5270634630613070443053417a47623152645942584d615869717479686977633533505078734344644e65636a6179494d640a6a4a56585054774c685472754f674d532f6270336763675777563334554856344c4a58474f4741452b6a625330686244424d6975644f596d6a36526d567368700a7a3947317a5a4353514e4d584861577345596b58353958707a7a6f423338346e52756c3251674574777a554e5239586c707a67744a424c6b335341436b76734e0a6d512f445738495748584c6738764c6e314c7a564a32653342313648344d6f45325443487871664d67723033494452524a6f676b656e517551734668657659540a6f2f6d4a79485357617656677a4d48473949356d2b65657046345779686a31593457794b41754d492b39644841582f68374c74385846435143683544626b56470a7a47723334735742416f4742414f73376e37595a714e616167756f7666496452527378785a7231794a41794473723677337947496d445a596a753463345759390a3565734f326b50334641347030633746685146356f4f623172427548455070333663704c346147654b38376361715466713633575a41756a6f545a7072394c700a4252626b4c37772f7847376a70512f636c70413873487a484751732f6e656c786f4f744337453131384669526776442f6a64686c4d794c39416f4742414e66580a76796f4e3170706c665432785238514f6a535a2b513335532f2b5341744d75426e4878336c307148326262426a63764d314d4e44576a6e5244796159686952750a692b4b4137747166696230392b58704233673544364f76376c732f4c647830532f56636d565774696132484b387938694c47746f6b6f425a4b513541614658320a695155382b744334683639476e4a59514b714e776743557a68382b674858355934366f4469546d52416f474159704f78386c582b637a42382f4461364d4e72570a6d495a4e543861745a4c4573447332414e455652784453496354435a4a4964372b6d31572b6e526f6179634c54574e6f775a312b3245724c765231302b4147590a62375973373957673969645961593979476e396c6e5a734d7a4169754c6579497658635371676a76414b6c565772684f51464f756768764e5776466c383559790a6f5753434d6c5069544c747437434373434b73674b7545436759426764497036475a7349666b67636c4b653068716776526f65553454523367636a4a6c4d39410a6c42546f2b704b686142656374706c783952785238416e73506f626271776361486e496641754b447a6a6b356d45764b5a6a436c6e46584634484148627941460a6e527a5a457939586b57466863383054357252705a4f3743377164786d753261694b69784d3356334c332f3055353871554c4544627562484d773962456841540a5075644938514b4267484545694d6d2f687239543431686251692f4c59616e576e6c46773175652b6f734b75463862585175786e6e484e7546542f632b392f410a76576867714736624f4548752b702f495072596d3474424d596c77737968346e5843794767444a4c624c49667a4b774b4157437448394c776e794456684f6f770a474839736864522b7357334577393778656630324b414834566c4e414e456d42563473514e7157577673597263466d32724f644c0a2d2d2d2d2d454e44205253412050524956415445204b45592d2d2d2d2d0a"
        val encodedKey = AuxBlob.encodePubKey(Algorithms.get("SHA512_RSA2048")!!, Helper.fromHexString(keyStr))
        val expectedKeyEnc =
            "00000800c9d87d7bc65551dd3224a2e00ebc7efdbda2538058697ef54a4087959054593d55caff36341afae1e0902a1a32685bf3dfad0bf9b1d0f7eaab471f76be1b984b67a362fadfe6b5f8ee73165fb8b182de4989d53dd7a842998175c8d8847bbd54a8226444bc3406103c89c2d1f32c036591b1a0d1c82156159948202774ef017a76a50b6bfde3faed0df90f7a41fa76053749fe344f4b0149e498f7898ecd36aa391da97d5d6b5a52d17569a8df7cde1c1bf9d9195bb7474cb9702eade5d6887ced926e460810b576033e09ac4db62ccd1200bdd4a703d31b910823365b11feaf5969b33c8824372d61bac599511897f92342969f872ecdb24d5fa924f245dae26526264d1efa3b53abc5d379532f66b82194669a936f3526438c8f96b9ffaccdf4006ebeb673548450854653d5dd43feb26a784079569f86f3d3813b3d409035329a517ff8c34bc7d6a1ca30fb1bfd270ab8644134c117dea1769aebcf0c50d913f50d0b2c9924cbb5b4f8c60ad026b15bfd4d44669db076aa799dc05c3b9436c18ffec9d25a6aa046e1a28b2f516151a3369183b4fbcda9403446988a1a91dfd92c3abf573a464620f2f0bc315e29fe5890325c6697999a8e1523eba947d363c618bb8ed2209f78af71b32e0889a1f17db130a0e61bbd6ec8f633e1dab0bd1641fe076f6e978b6a33d2d780036a4de70582281fef6aa9757ee14ee2955b4fe6dc03b981"
        assertEquals(expectedKeyEnc, Helper.toHexString(encodedKey))
        run {//decode pub key and check
            val decodedKey = CryptoHelper.KeyBox.decodeRSAkey(encodedKey)
            //val rsa = KeyHelper.parsePemPrivateKeyBC(ByteArrayInputStream(Helper.fromHexString(keyStr))) //BC RSA
            val rsa = (CryptoHelper.KeyBox.parse2(Helper.fromHexString(keyStr)) as Array<*>)[2] as RSAPrivateKey //BC RSA
            assert(rsa.modulus.equals(decodedKey.modulus))
            assert(rsa.publicExponent.equals(decodedKey.publicExponent))
        }
    }

    @Test
    fun testEncodeKey4096() {
        val keyStr =
            "2d2d2d2d2d424547494e205253412050524956415445204b45592d2d2d2d2d0a4d49494a4b51494241414b43416745413241537634394f456248344e695433436a4e4d5356656c697966455058737757637174456643786c53705331466973410a757762764577645454506c6b7553683647345359694e686e704350357030766353672f334f686975564b67562f724374724458614f36306e764b2f6f307938330a4e4e5a524b3278614a396557427139727549444b2b6a43307359577a546171717778593047726a6e782f7235435865726c355072524b3750494c7a77674248620a4977784863626c74316e74675234635756704f337769716173457742444444596b3466773757364c766a42623971617633594238525636506b5a4e65525036340a676766756563712f4d584e69574f504e784c7a434552326853722f2b4a333268396a576a587372635679382b384d6c64686d72347232616e37633234376146660a757075464774554a7270524f4f382f4c584d6c356750664d706b716f61746a544d52483539674a6a4b686f743052706d47785a42766233335463424b3553644a0a583339593479637435636c6d446c4934466a6a3746757454502b623936614a654a566e596555582f4130776d6f6742616a734a526f525835652f5263675a73590a527a58594c515870725138316442576a6a6f764d4a39703858655436424e4d4643376f36736b6c464c3066484455452f6c34424e5038473175334266707a65760a534349535253373144346553346f51422b5249504642556b7a6f6d5a37726e45463342774665712b786d7766597250304c5261482b315965526175754d7552650a6b6531545a6c36393761336d456a6b4e67386e6f6132777470653745576d61756a4a66584457784a782f58456b6a474c4365347a32716b33746b6b592b4135670a5263677a6b6538675678432b654332444a74624b59666b76344c38464d464a61456877417031334d664337466c59756a4f2f42444c6c3764414e7343417745410a41514b43416741576f4c38502f57736b746a755377623573592f764b74677a634848314172393432477379737554585044793638364c7046335238542f6a4e790a6e376b3255424169613878536f5743523642625275486556356f412b504c47654f704537516153666f6e422b79632b63793078334f7233737366714573752f710a746f47487037352f38445853365745304b303478393475317264433962397350727247426c57434c477a714d306b62754a667948586464336e32536f6641554f0a62355152536778442b327448557045726f4871486e574a436166344a30516567583435796b746c664f594e4b2f50484c44515856386c792f656a6333324d34590a5476376855744f4f4a54757138564367394f575a6d325a6f3151754d3958454a54504370356c332b6f35767a4f3679686b32676f7444764433324364412b336b0a744c4a525035344d31536e2b4958623167474b4e39724b4174474a62656e5749506c4e4f626851676b627747383951642b3572664d587369507631486c31744b0a2b7471776a4438322f48332f456c61614d6e77484370656f47537039354f626c416f426a7a6a4d50324b7362764b53644c384f2f7266316333754f77392b44460a6374683053413879335a7a493131674a746232514d475572436e79356e34735047476263337833384e644c6877626b504b5a7936304f69543467326b4e7064590a644969746d414d4c326f747474694634414a4d36417261506b3859567a6b504c546b736f4c33617a50427961356c496f444932483351765474537670586b58500a794b6368734453575962647166706c71432f5830446a70322f5a64386a704e3549362b3161536d70546d6277782f4a546c6c59314e383946525a4c4964786f680a326b38314c5069586845367552626a696f4a556c626e45574970593279324e32436c6d78706a68302f496358643158496d514b4341514541375a61692b796a6a0a387869743234614f395466336d5a4258426a5361446f646a43324b533179436341495870365337614830775a6970795a70516a7973337a614251794d525946470a625171496656416136696e5779446f6f6662414a484d7535425663484642505a765353355968446a6338585a3564715343787a497a396f70497141626d2b62340a6145562f3341334a6b6935447938792f356a323147414b3459346d71514f597a6e653762444769334879753034314d474d3471664963496b53354e31654857340a73445a4a68362b4b357475784e355458336e445a53706d396c754e48386d4c47674b415a313562314c715841744d3579636f4259394876303832737550506f6d0a4f2b72307962645258366e445348382b313179324b6950326b645649554843476b776c716772757835595a796a435a50774f764550687a536f4f532b764269460a555658413869646e784e4c6b31514b4341514541364d496968445358782b33353066577168512f3351633667412f74324331354a774a392b754657412b676a640a632f686e3548636d6e6d424a4e345230346e4c472f61553953517572383761346d6e432f4d70394a4941526a486c5a2f574e54345530734a79504556526735550a5a3956616a417563577769304a794a59434f31454d4d7936384a7038716c5472694b2f4c376e624438364a4a354153786a6f6a694e2f3070734b2f506b3630460a52722b73684b5069336a52513142446a447441784f666f346374662f6e4662554d34625930464e50514d50375765736f534b55304e42435252366430643274710a59666c4d6a495148782b4e373450356a4564534348545647516d2b646a3437705574336c4c504c576330625831472f47656b775850344e5573522f37304873690a6277786b4e6e4b325453477a6b743272634f6e757450313235724a753657705637534e727139726d37774b43415141664d524f636e625776694b48716e4450510a6864522f324b39554a54764568496e41534f5332555a5770692b733172657a394275536a69674f7834776261415a3474343450573743337579743834644866550a486b495162334935626738454e4d724a704b394e4e3333796b77757a6b44774d537746635a2b4763693937685375627a6f4d6c2f496b6569694e314d61704c340a47684c556773442b33554d564c2b593953796d4b383633374967796f434764694e44362f535873613853774c4a6f3356546a717834654b70583763766c53424c0a52725278633530546d775573416873643443446c39596e5341544c6a56764a4265596c664d32746246506159776c31615238762b50576b666e4b3065666d36300a66486b69333348456e47746542504b7547713476775659706e3662594777517a2b66363333352f4132444d665a484653706a56555248506352634862434d6c610a30635578416f4942415143323565594e6b4f3437386d6f2b62426245584a6c6b6f714c6d766a417947724e466f343846396c705648365930764e75576b584a4e0a5055674c556841753652596f746a47454e71473137727a387a742f505059394f6b325033734f783874303079316d496e2f686c445a58733535464d30664f4d750a505a616973634150733748447a76794f6d4461682b667a692b5a443848324d33445332572b5945306961654a6132765a4a5332743032573042475869444933330a495a44714d794c59767777506a4f6e53684a7964457a58494434784c6c30744e6a7a4c786f3347534e41376a59716c6d627456384358496337724d534c3657560a6b7449444b4b4a636e6d706e3354634b6558364d456a615349543832704e4f5333665933506d58754c2b434d7a6677382b75373745656371373866486154694c0a50354a474d393346366d7a693139455930746d496e55424d435774514c63454e416f494241514367304b614f6b6238543336717a5072746762666f75304532440a756664704c3175676d443465644f464b51423566444651684c6e534556534a71334b5567346b57735861705164734264366b4c6478532b4b364d51724c427a720a34746630633755434631417a576b3677584d45785a386d526232526b475a595142324464796846423354506d6e71394357384a43712b366b78672f776b5534730a764d344a587a676371566f53663432514a6c2b4239776165576867304254577830316c616c34647338384876454b6d4530696b354777694462723745764444770a453655625a745163496f535449495a4467597156466652324441686f3377584a52734f58683433336c454a3858376343447a726e674662516e6c4b7270774d4c0a58676d30534955632b4e6635706f4d4d3372664c464b3737742f6f6234772b355077524b636f536e69794178724864366277796b59413856757964760a2d2d2d2d2d454e44205253412050524956415445204b45592d2d2d2d2d0a"
        val encodedKey = AuxBlob.encodePubKey(Algorithms.get("SHA256_RSA4096")!!, Hex.decodeHex(keyStr))
        val expectedKeyEnc =
            "0000100055d904add804afe3d3846c7e0d893dc28cd31255e962c9f10f5ecc1672ab447c2c654a94b5162b00bb06ef1307534cf964b9287a1b849888d867a423f9a74bdc4a0ff73a18ae54a815feb0adac35da3bad27bcafe8d32f3734d6512b6c5a27d79606af6bb880cafa30b4b185b34daaaac316341ab8e7c7faf90977ab9793eb44aecf20bcf08011db230c4771b96dd67b604787165693b7c22a9ab04c010c30d89387f0ed6e8bbe305bf6a6afdd807c455e8f91935e44feb88207ee79cabf31736258e3cdc4bcc2111da14abffe277da1f635a35ecadc572f3ef0c95d866af8af66a7edcdb8eda15fba9b851ad509ae944e3bcfcb5cc97980f7cca64aa86ad8d33111f9f602632a1a2dd11a661b1641bdbdf74dc04ae527495f7f58e3272de5c9660e52381638fb16eb533fe6fde9a25e2559d87945ff034c26a2005a8ec251a115f97bf45c819b184735d82d05e9ad0f357415a38e8bcc27da7c5de4fa04d3050bba3ab249452f47c70d413f97804d3fc1b5bb705fa737af482212452ef50f8792e28401f9120f141524ce8999eeb9c417707015eabec66c1f62b3f42d1687fb561e45abae32e45e91ed53665ebdedade612390d83c9e86b6c2da5eec45a66ae8c97d70d6c49c7f5c492318b09ee33daa937b64918f80e6045c83391ef205710be782d8326d6ca61f92fe0bf0530525a121c00a75dcc7c2ec5958ba33bf0432e5edd00db0db33799a9cd9cb743f7354421c28271ab8daab44111ec1e8dfc1482924e836a0a6b355e5de95ccc8cde39d14a5b5f63a964e00acb0bb85a7cc30be6befe8b0f7d348e026674016cca76ac7c67082f3f1aa62c60b3ffda8db8120c007fcc50a15c64a1e25f3265c99cbed60a13873c2a45470cca4282fa8965e789b48ff71ee623a5d059377992d7ce3dfde3a10bcf6c85a065f35cc64a635f6e3a3a2a8b6ab62fbbf8b24b62bc1a912566e369ca60490bf68abe3e7653c27aa8041775f1f303621b85b2b0ef8015b6d44edf71acdb2a04d4b421ba655657e8fa84a27d130eafd79a582aa381848d09a06ac1bbd9f586acbd756109e68c3d77b2ed3020e4001d97e8bfc7001b21b116e741672eec38bce51bb4062331711c49cd764a76368da3898b4a7af487c8150f3739f66d8019ef5ca866ce1b167921dfd73130c421dd345bd21a2b3e5df7eaca058eb7cb492ea0e3f4a74819109c04a7f42874c86f63202b462426191dd12c316d5a29a206a6b241cc0a27960996ac476578685198d6d8a62da0cfece274f282e397d97ed4f80b70433db17b9780d6cbd719bc630bfd4d88fe67acb8cc50b768b35bd61e25fc5f3c8db1337cb349013f71550e51ba6126faeae5b5e8aacfcd969fd6c15f5391ad05de20e751da5b9567edf4ee426570130b70141cc9e019ca5ff51d704b6c0674ecb52e77e174a1a399a0859ef1acd87e"
        assertEquals(expectedKeyEnc, Hex.encodeHexString(encodedKey))
    }

    @Test
    fun testEncodeKey8192() {
        val keyStr =
            "2d2d2d2d2d424547494e205253412050524956415445204b45592d2d2d2d2d0a4d4949534b67494241414b4342414541304433542b644953736d43486d37393777735830765666715557444a2f336d7644596f7a6c43616244686e474c6c53450a7041516266315a3854732b4f4d34705652484f4a554a4c305765624e646d5050476a737957517a367a5a4539366c515a4c3361764345587159565152363656350a3377644b2f6f68614d53526e4779454d4272716b56566246336743722b2f6972784433594b2b566f774f32574b732f3647724d647154413859354354462f4a650a707477735367354d4d6a723655614b3471446372656a33686b6742564776525633636a31736e4b3642723848755964466e7047475453306437554a6c4846676c0a74724748552f43424f393233686b48674a6157456a4330676953476a684b4b744c7a72566370445632792f6c57515039542f5434646a454149614871512b2b500a53644f535236707349475236685667536967743748436e45376e573731312f7266563555723945695670423034306d44496d4b5a6379382f2f544d6e5879644e0a314b595456642f33346664707a4d7053773569626c457262774f4c585654556d4f7a74596e706c343166654853762f6a506573487374506c666b6c494632766f0a475a456f6866397363517663754d3777454266432f615441394b33397a4d6d6b42626376535a6a4c79686d63535a574d50504f5a7949636c337a5935335168570a51432f61626d49634266493153342b72376d433469324a6e2b2b6f457675474e564772325359325a305a5a7858474c3148492f3038442f332b5463756d72636e0a34596a504b2f444d466930462b652b317834316c697075662b63782f3271524e51582f6d30325354724c59644d3665306733334b766c6e4664693262373532790a2f4f49614d777844614a76756e4d6836454d44574b4d31414862592f696f416f4b376553323648654a4c45446c6c714f342b535750333763386c4d76534557790a314769457252304863734f6a2f51775747504673656f56726f4d6941327355513049632f7467566a43546c58672b31325870556e6f754977654369384b634c2f0a6164327a4a6b6a75396842684a4c42512f32476e69764a69336c4667463447642f2f54534a36726757755846664d4b742f397a32537a33356f684558347941300a666c716c43654c496e46456f6576627a2b585439615266446536354d5a373979773354665039437256373468663152527a766544347a706933462b68635932690a4a5773483767524f5a65436d3666415835547265636433684f784a4f6641344e34727653534371364277437665625438465932355a2f564637635172485944530a696a3577366c71684d7a5848655545593930476139414b34587a6157774767657a712b52375a73303059534b7146763971594e4b645237747a33636a696a57660a39712f33523175683645514b544d5a4b6f345345436c4a6947796a4f42766d504b30396a4d465a544a763030684478616744505a426c3758704c444a352f4c6e0a31757070764c434e575759317a654a6661456c4d7971332f50714b5a4c6964463972566f41315349776b326c70645576506f7465326f466977435a6f586c775a0a4a326e636a6d5867514e7337362f38756e444a4130726a344a5071636377344d35477851376f6b62676d334634726d7a72694375763842654d53436b723272790a306d59335568706f68583477434d713047347835734555417a39465656505a4b6a786e59426d4c447a724a41522b342b4737675a736374303158444a596744640a4a5659496e465032322f63497265385672465759744862674f46644e7155695671353864653650645a472f452b7561576d455468536c527267456a54787570690a4f586667644b572f32306a317141746a4f6c714677735930393451357271554c513677507851494441514142416f4945415143686d6b6d6c68725242763432640a6659556979784b353262386174683073614a64447a36746c586d785944674a784d392f586c4f5274396f547a65446b6e6f454f356f6c752b72727834424267510a747a59696169775256585252455654575137746a7a5276614e4c2f47466b4c7439335854636370754b7779724e452f6269744c56616752627763492b485a46610a4d6b6e434f6968484d486f52746f386833464b41593934787a5341674f444d656b315747386a6867704358586d564e6e4250742b64346f44444944414741667a0a71676630334a356e6849622b38304b675a4f7a504f4b6e62764a614c36456d6c4c4862674233633432647a417737684874566d6f6659475763764c62324d49590a44564b4f3433352f73517831552f384e4448364a6a566441435a6a4c674f625848394b332f54743436445750456372504c6d443878686f633667464d2b5172300a41686b7a4b6f4259444e6b30436c6a6268644942586a6b74585536775251465a343575503265344a5a347a727a47424c722f74346c5461765a305351744c6c640a41366b4f7347682b6443574644746e736878596e6c2f7861642f79522b3361357a6d444a626f2f664a544258726c663142347266516b46744b323065744f50510a422b2b46432f726a68334d6d2f4b622f7039477a2f3275705a6441724839375a7644324c4246666a37376c466d4168714169337743526c4e2b656b755978615a0a743170425639795869673844796c64673164375838704f6e326b7972463372515544446634706137783976706e626b556c455569666f5639676e59736d646e690a71447a59427454763267364d4b7177517953586149555730594f4250624f656c6c574577784a7147595137793449665648664d306979486e65686b32745a63720a2b58617a4c6e7747652b427a347663677546684a584c7949752f2f6c414f685a74626b367231514a45557578614f4f515833777a79636545366e6b4473676d720a5035646a335a7064376653325656327679474849466e424a38384c52787265567667723651323855543237534238327a4d62376d525a545645327a65757562540a354432443158625a3077426f3657694b366552527244513248616565746b6a2f756f527936505758776e4161546d6d49727258774c71616f4a682f5531652b440a746673444c57643649784c6a66587647676c7248737274417a306f70727069785554655668675472476b394951526435727678754755596846756a56615949360a2b5155662b3333414664746e636238793943396a5a6d677838414b624a6b2b653733534c6842354a566f732b57746555376238642f4d696d356d414c6a6e4f360a5a316e2f75696d735437397353447179335853796d744b57586f2f3232556c72764743706f4575454c504d6236645346575237767772737668466e6759342f4b0a556e69746e7678626f45666c516e614951344966524c527a5a73582b73433545737177395535744874346f492b39314476334b626462634552675637334b36420a5a516743346c6b415171754658695a354149436b786a694d795a77547455394b4a37787631375875366f7977462f33417462564745545731442b336d614873440a7933444153576f6a79715a644c6a2b57477a4b5152612b737767434441594b65656b32664941584653644636337a784a3252784f4a3447696a53616f682b6d720a3448567663704461546a2b413854312b516442794d347339386775344744376b5674565147425a64576a75747948766830685776316774566d6268512f3431330a67444d4646447a48496a4c5459475965733468484c32323136396a565239735a316551787776544967334e3470443563466d307252755a5a54532b6f4a546f460a4732376142466968416f494341514479564236325a446e62785174686b2b7a49544b497a5255724a624c6f58725563414e63534866614e37696e4638374f76610a7a6537656a5439444e5345686274665a464a31473664694f596f53772b324d7a4658763067456b4c4b593064455479644b67484575366e5671356569764d67760a4434686339596b4a4d4844536c6d763246446b704c33415843416d6e5739724b702b64647474425a45436e6d6c504570484c6f6a367867427733704e613158730a49634c56666475674838364865786a366f306f4b6759666371725838555548745549322f585171674672496a386b736a6631664656574a524a46576d425871700a6e4d45735961727a4154654d316b512f6b446554315a55706f4750517430322f5871585434423541334154694574704d32752b6c343878746f675757673252790a47396c3933385374416d68556957316d37476e4b453645494676515938355776627a784f52304a5956555372374d72617346366e6e516c6859784675494a6f4a0a32682f4b4a51616f354743547647342b4774624a4a6d3463326e795a67777968697a4d7364677364636c7337396158694d6b725a5a6b616d4c56555a574f74450a3370412f6f42757a32716e4f3948776a62483148474f636371305458666d70465363455633435147594a646e6f3646793763626d7570614c34553961675134650a772b79674c31386e713548562b2b4c5374466e567267733559696a6a736b665264453947554d56446835704373643959323346796d616164344f2f32535243430a596b53737948354f7679444f4c706f79554a366736512b343548716d2f336c4734596a4e707a4655694d636e70372b33785533357143304c4b387845666565690a4d73316d54564569484e49703678482f547152645837335744372b59754b5a534c496652473764677269725536772b6d686876784435317548514b43416745410a322f316d42435235716d332f304c742b2b52516265794533746977343055657951717563472f2b567659373773534c6b492f4c78386977526c797758634c426e0a2b4134547667756b6d4164577a4373386e64674b4e7850412b67666f687642734d4f474e394b4f4231556735767667324a326b6949363476775943777a68645a0a4e5455556d4c2b474d464855715373575967366937694246635a6d7a6e72345732543362427879544d5a6b69374a53744238366533354b58727a63322f572f620a2b2f70355532484353617a444849356d4d7975436c486336476d5553564a3766374c486a4c39346a76694e716f627030566a3630337453634849536d4e725a770a544261766b765a475958736f574b767161766b376a424239517a61424c2b756e614652736c67356a5461694b6e49536a3434557331666a464b7538347869664c0a6e4a61457a6a445074375042786b6f374c5067455937774633396e4d3956706f65744937627752364e77444c535838555539374d47642b48592b4d4f315769310a7064324c61707772782f454b374f787a33333556524b344a6530615a6e61346a32547951644d4a616339667347505876345a734c66444c6a2f7744366c316a2b0a6c4c4c62427633496d64536a33324c4262687367463469434765584f384870504f2b512f68395856736e593532556d3258644e4d6e30335043476d365a76744d0a37445869532b6c50463930486a6f6c4a56485a54424e74645652724c7235337a4c7557456671543446654b72446178647469586b784c6a72422b352f565975370a6e74796b30315a513633564e664577533169726d4b6c392b715a6b54486b33484856396a4e5635527a575669776d4a493757707231597a42776d634b4342314f0a6f4755414444733851706e6b437a30786b4d56745977486a39714b5a6c716662487a72464455556346386b43676749416459765563676a662f2f6a75386d41380a3556513341635045365476796350572b6b5232447657313256634473462f73633155413764487a7a695068476e3938536d4e786c426a62387375536246505a380a5168565430574242446b6354696c774947507839617837553353366c47573256645336467151483566526d67514b5a79724356584c4f457a384267594272534a0a78752f335451415778483051746962646247486738506469353867596c574652686e394238536c6831615259484750623141684e4c4264302f6464592b3547320a397853794458646d5a67316355412b42337a41774e5371627a46786870327a552b563175587362706b344b746e595636435a4d39516c7243526a546b39694e550a645658462f716169526a667a726d3453736d4570436b4562737270374632325931626b6f6f4f52676c4d4f734e41574e716656587734774e2b7379586a31726f0a36765a3850455259724679414f52316473514d4968796d6e6d54506a4370614a34656d4b7268575479323073593731746848616b5a574a633232596f4e70625a0a453674674956734a50546c78672f342b667943434b6a3577577239326e687342314b425a50474f2f7a4668764d6c4a70765130744838573270624e3261306d490a3578394671414c6d2f716a774348665a49745377504d2b5a6f7a53687433634f6b4748646344354b58415866636673444a633453485a4b56497a71344e75734e0a353034522f6a76443147503873676c7947376f6d703735636b677a416d616b4c64784f503248685176495839746358705369724e4a36536c3262774b75754d460a77786f33722f6f2f3959393765344c6c66704559703965714d6463472b4e705239393349774b30556841575339483577646e57425355486435653478744455740a69494c4e52754f34366737522f4149687a3163535372615757516b4367674942414d6868505035433979743950496d316230655477434263746e465351494b6f0a4b734139726c6c3261622b624d4c6b396a63384d364d4c737a7930437457736f30397348663459593974696676726b4548526574684568387a736377557559750a736d326e31665469786b30756c364c5356676c35347558624d4a6179454e6e3450494b526b65773863413874536d613433343937773337686d442b4d674362310a414c7a7163636f3968666d6b676b4936666f316738436533554545434b7932594b536d5245646759634b394a46514f36315736416b46574a634478416d667a490a4a6a466b4b7773623754537737397a57694564536f4d396a6d377343504b41546436426d2f5a41416b5555547545466b666f626e39417831724a4e2f587862320a4d4b754155745176304e595930674556644736326a4974754b4c4964366e6e63483850472b7273526a504c495970577159644a704b783570556e522b34416b510a5336437352415377634634506442764444424946473658706a466f347050645168447a4c32735446386238535753424c6c4a516262374736554e7167435361750a537573434670617a7655354e66446d554d7563746f623245595661535871396a47616a366254556d447758487757696c66496b3958664c786e59665859724a360a786864497058476d4868754c517441674b324f314a744c6f50633973397150382f536b665037786a6a4736784873502f57764c37514531705073395a4d2f55490a4330314a4e484669394c4b436e386f356d625a6a4e386a556f77693766664b2b3736775a5547314c377a4d35797457514f59776f3054514266633866706d46770a2b5242524a58326b4a79444f32374578637a6f474f4b6a77714544614f444942392b397a63434b304267536f526962536d345a42766f787a57574436354b6c730a786450685a55486346475735416f494341514338694732376144386152557439344f656b363667464f4a78383451565a6568575071745a6a577956656e4475630a543864696e6b386f656a476a634b32554a755144613833617a7639306f63567145306e30726f6e5979737a74394962316a6c59432b434b3141723954594746670a5755354f57454479437a437071572f772f61473638553871684b6d304d766b4c4a522b47366576616e3954774568464556416d3369576c6c4e587339783239730a42756377794d4d4332337a73696d78596c53376441344474797656412b7a4c316f6d4c7053574862552f71747549334856314e654a7a73792b6743346d7750680a6a353274646c3636396679574c7a487a42524c65713664564f65646a6e436f2b6a6c5533644c323044456b39536157303844314350755a656b56316a56504d770a4a6f614463495268344b4c74513042595a37554a6546555473783143532f2b55717a715953504f69353761356b76723059385977526e534238644856467474580a4a5476383377545158485046534267666e484e65376c7352546649516675496b723262706955376838355551374c7371634936594861433037555263734746460a46724c5747683931717a416431646953486c6132526e59336e385050754d6e436b67754e684c557259646d794d6f6c374666574661396c77706c7375547a42710a4236796a3869616945334c4c2b512f65756c4a375336515066414932625530554a4f323359346b6f656f4969624545444d534351364b595a324e436c525252540a67613566533159666b44464563485551312f4b496b64594847424b426a6f4b4745787a69382b436769537953565359445a6c3677494f684c6a48324f5a336f6c0a6c64504e37694e41486972727867397638514f364f516c704c556b354c68702f3164536c5a36737933556a4671766178337477365a6a724c3838595035673d3d0a2d2d2d2d2d454e44205253412050524956415445204b45592d2d2d2d2d0a"
        val encodedKey = AuxBlob.encodePubKey(Algorithms.get("SHA256_RSA8192")!!, Hex.decodeHex(keyStr))
        val expectedKeyEnc =
            "00002000ef8168f3d03dd3f9d212b260879bbf7bc2c5f4bd57ea5160c9ff79af0d8a3394269b0e19c62e5484a4041b7f567c4ecf8e338a554473895092f459e6cd7663cf1a3b32590cfacd913dea54192f76af0845ea615411eba579df074afe885a3124671b210c06baa45556c5de00abfbf8abc43dd82be568c0ed962acffa1ab31da9303c63909317f25ea6dc2c4a0e4c323afa51a2b8a8372b7a3de19200551af455ddc8f5b272ba06bf07b987459e91864d2d1ded42651c5825b6b18753f0813bddb78641e025a5848c2d208921a384a2ad2f3ad57290d5db2fe55903fd4ff4f876310021a1ea43ef8f49d39247aa6c20647a8558128a0b7b1c29c4ee75bbd75feb7d5e54afd122569074e34983226299732f3ffd33275f274dd4a61355dff7e1f769ccca52c3989b944adbc0e2d75535263b3b589e9978d5f7874affe33deb07b2d3e57e4948176be819912885ff6c710bdcb8cef01017c2fda4c0f4adfdccc9a405b72f4998cbca199c49958c3cf399c88725df3639dd0856402fda6e621c05f2354b8fabee60b88b6267fbea04bee18d546af6498d99d196715c62f51c8ff4f03ff7f9372e9ab727e188cf2bf0cc162d05f9efb5c78d658a9b9ff9cc7fdaa44d417fe6d36493acb61d33a7b4837dcabe59c5762d9bef9db2fce21a330c43689bee9cc87a10c0d628cd401db63f8a80282bb792dba1de24b103965a8ee3e4963f7edcf2532f4845b2d46884ad1d0772c3a3fd0c1618f16c7a856ba0c880dac510d0873fb6056309395783ed765e9527a2e2307828bc29c2ff69ddb32648eef6106124b050ff61a78af262de516017819dfff4d227aae05ae5c57cc2adffdcf64b3df9a21117e320347e5aa509e2c89c51287af6f3f974fd6917c37bae4c67bf72c374df3fd0ab57be217f5451cef783e33a62dc5fa1718da2256b07ee044e65e0a6e9f017e53ade71dde13b124e7c0e0de2bbd2482aba0700af79b4fc158db967f545edc42b1d80d28a3e70ea5aa13335c7794118f7419af402b85f3696c0681eceaf91ed9b34d1848aa85bfda9834a751eedcf77238a359ff6aff7475ba1e8440a4cc64aa384840a52621b28ce06f98f2b4f6330565326fd34843c5a8033d9065ed7a4b0c9e7f2e7d6ea69bcb08d596635cde25f68494ccaadff3ea2992e2745f6b568035488c24da5a5d52f3e8b5eda8162c026685e5c192769dc8e65e040db3bebff2e9c3240d2b8f824fa9c730e0ce46c50ee891b826dc5e2b9b3ae20aebfc05e3120a4af6af2d26637521a68857e3008cab41b8c79b04500cfd15554f64a8f19d80662c3ceb24047ee3e1bb819b1cb74d570c96200dd2556089c53f6dbf708adef15ac5598b476e038574da94895ab9f1d7ba3dd646fc4fae6969844e14a546b8048d3c6ea623977e074a5bfdb48f5a80b633a5a85c2c634f78439aea50b43ac0fc51af9112e97f272e5448cc3110fce7a4081ea0613cba5637de45982ff7144d57cb6401e5186d39755af273947e526d5bb5bd4390562cfb8f28039c14be94986fb4f79a49bb97a30d2f252a8569e121993af3c57a60e98cb07dfedbdeffc887ada8d098ac4deef25fee3b33ce90be9d27ae66602da89b4b931a08ae776e7ecac8ccb30c02b72208b87f05b03b9cf83f66e4be8344fd4c4ddd08194ad7a0a3a6d6ec21058e6de8bf9c8691cb7994748e71989387340a5ecd3cd0f506b6686522673e9d2770cd13db18217cd261f706efcc0f4b3842982e4c8d333b3e0ae9348b91e15843e17371426b8294964f2e74cd1c2e08a575b66a79494d98f1db480edc741f689a3678985c379609bac92b0c192dfe5ea44fc284cf67297220888210d9ebd2d100ffc8bcb13636aad6263b2a735e3d006f309100dc391297ce7253e6df1885eefee0e7035ef318053d7177cd4acc5ec92031a1fd66def3404c4adfff0f12194a093fad509a0a6c7422011d0b0c40a6da8601fc71e48ad2544ebc652499ed591e6eb89b074fca8a920b8bbb568abfcc0af73fa4f3256cb30609cdbd11307db751d0835e09cfa1c747a58ed2919d4bd6a9a8eb2c1fb92e003bb090de173dc704b387f41b7285f95294d1ce756b269e9e3814ccb3f80efe0d7eac6d0cfe2cc51524aa4c984476d74fd34f3e4ff126cf01523a1deda0e5a0772f0e61bed8dc3615d547d8113d23d8f0bfc0097ae88258e6478cee389fbb6bf4ea68d083a938d7f781ce370c03cbd10a053e7d6a37526f610220eb684805e62204f210a790e7009cf71fb036851f5fcdb86bf640c94e0c0a412a66abf5a7f205d515b6c85d65b7da2f7e40dcf3d0567d2d851f02942a0f9a67e7f9f4427460de297d17219b940ddcfaf8310d6e906831be39c2cf1d527f2ffabd95dfe145c8f0adecfc4b167f38ae03fd8d2fe4d42bd8f2ae20f59a3688153a8b2c75ad360bd9a900d810d5ce17a5be35436cd15d6b2b881b56fd97277e4b2cd82f25df35286ec724a125280a036bcb602bd0038c5ea3026547462a06e4a45729ca0784e31d1103b7f991eaa89d7121cfd67510fb18d4d5b03068884c592d6bad56815d4eb4cffab1feb7ea27dd4e28c62db6d18f8d838b48553cd737611657463349f70cdc260ab0ad9bf163658eacc78982a274f85aadb0becf92588ecd53fc5e229bb1fe870a5f18c5c66bd154d052b2e2663004c0d6beacfcb55094ffb1898b7dbe3c9653815da4c11d53ac018b98fbb36fa61197de15258dc4614807c83c02f15420527508e63f8327b4c9862291810ff453b9badb3d7620d8c1aab8b5d50d6af59fc181161a5b1039090062f0c8995822be2df158447253b8abf913225c1bdef3e9a54a0589c1f69580e2565b28c75e9c4c8d738504ee8e08de414c64d8cee4864ecf9a60948515cc07680"
        assertEquals(expectedKeyEnc, Hex.encodeHexString(encodedKey))
    }
}
